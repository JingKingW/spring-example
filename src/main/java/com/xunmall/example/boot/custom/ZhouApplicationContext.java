package com.xunmall.example.boot.custom;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyj03
 * @date 2021/9/27
 */
public class ZhouApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Object> singletonPool = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ZhouApplicationContext(Class configClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.configClass = configClass;
        scanConfig();
        if (!beanDefinitionMap.isEmpty()) {
            for (Map.Entry<String, BeanDefinition> item : beanDefinitionMap.entrySet()) {
                String beanName = item.getKey();
                BeanDefinition beanDefinition = item.getValue();
                if ("singleton".equals(beanDefinition.getType())) {
                    Object instance = createBean(beanName, beanDefinition);
                    singletonPool.put(beanName, instance);
                }
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object newInstance = clazz.getDeclaredConstructor().newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    if (bean == null) {
                        throw new RuntimeException();
                    }
                    field.setAccessible(true);
                    field.set(newInstance, bean);
                }
            }

            if (newInstance instanceof BeanNameAware) {
                ((BeanNameAware) newInstance).setBeanName(beanName);
            }

            for (BeanPostProcessor processor : beanPostProcessorList) {
                newInstance = processor.postProcessBeforeInitialization(newInstance, beanName);
            }

            if (newInstance instanceof InitializingBean) {
                ((InitializingBean) newInstance).afterPropertiesSet();
            }

            for (BeanPostProcessor processor : beanPostProcessorList) {
                newInstance = processor.postProcessAfterInitialization(newInstance, beanName);
            }

            return newInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void scanConfig() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan declaredAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
            String configPath = declaredAnnotation.value();
            String filePath = configPath.replaceAll("\\.", "/");
            ClassLoader classLoader = ZhouApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(filePath);
            File configFile = new File(resource.getFile());
            if (configFile.isDirectory()) {
                File[] fileList = configFile.listFiles();
                for (File f : fileList) {
                    String absolutePath = f.getAbsolutePath();
                    String scanPath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class")).replaceAll("\\\\", "\\.");
                    Class aClass = null;
                    try {
                        aClass = classLoader.loadClass(scanPath);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (aClass.isAnnotationPresent(Component.class)) {

                        if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                            BeanPostProcessor instance = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                            beanPostProcessorList.add(instance);
                        }
                        Component componentAnnotation = (Component) aClass.getDeclaredAnnotation(Component.class);
                        String beanName = componentAnnotation.value();
                        BeanDefinition beanDefinition = new BeanDefinition();
                        String scopeType = "singleton";
                        if (aClass.isAnnotationPresent(Scope.class)) {
                            Scope scope = (Scope) aClass.getDeclaredAnnotation(Scope.class);
                            scopeType = scope.value();
                        }
                        beanDefinition.setType(scopeType);
                        beanDefinition.setBeanName(beanName);
                        beanDefinition.setClazz(aClass);
                        beanDefinitionMap.put(beanName, beanDefinition);
                    }
                }
            }
        }

    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getType())) {
                return singletonPool.get(beanName);
            }
            return createBean(beanName, beanDefinition);
        } else {
            return null;
        }
    }
}
