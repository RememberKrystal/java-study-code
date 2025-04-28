package com.remember.test;

import com.remember.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/14 10:10
 * @Description :
 */
public class ApplicationFactoryTest {
    public static void main(String[] args) {
        // 创建ApplicationContext对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从ApplicationContext中获取bean
        UserService userService = (UserService) context.getBean("userService");

        System.out.println(userService);
    }
}
