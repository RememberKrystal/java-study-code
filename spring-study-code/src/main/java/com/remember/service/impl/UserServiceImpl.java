package com.remember.service.impl;

import com.remember.dao.UserDao;
import com.remember.service.UserService;
import org.springframework.beans.factory.InitializingBean;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/14 10:09
 * @Description :
 */
public class UserServiceImpl implements UserService, InitializingBean {

    public void init(){
        System.out.println("UserServiceImpl初始化...");
    }

    public void destroy(){
        System.out.println("UserServiceImpl销毁...");
    }

    public UserServiceImpl() {
        System.out.println("UserServiceImpl无参构造实例化...");
    }

    public UserServiceImpl(String name) {
        System.out.println("UserServiceImpl有参构造实例化...");
    }

    private UserDao userDao;

    // BeanFactory去调用该方法，从容器中获得userDao设置到此处
    public void setUserDao(UserDao userDao){
        System.out.println("setUserDao执行...");
        this.userDao = userDao;
    }

    // 在init方法前执行
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet执行...");
    }
}
