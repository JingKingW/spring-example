package com.xunmall.example.boot.simple;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wangyj03@zenmen.com
 * @description
 * @date 2021/6/25 16:23
 */
public class BeanFactoryTest {

    @Test
    public void testSimpleLoad() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));

        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");

        System.out.println(myTestBean.getTestStr());
    }

}
