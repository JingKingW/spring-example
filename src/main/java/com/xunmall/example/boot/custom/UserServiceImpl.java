package com.xunmall.example.boot.custom;

/**
 * @Author: wangyj03
 * @Date: 2021/9/27 20:18
 */
@Component("userService")
@Scope("prototype")
public class UserServiceImpl implements UserService, BeanNameAware, InitializingBean {

    @Autowired
    private OrderStorage orderStorage;

    private String beanName;

    @Override
    public String printlnUserName() {
        String goodsById = orderStorage.getGoodsById();
        return "Hello World " + goodsById + " " + beanName;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }
}
