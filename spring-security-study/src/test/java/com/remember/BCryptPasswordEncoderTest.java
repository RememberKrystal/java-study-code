package com.remember;

import com.remember.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/11/26 23:29
 * @Description :
 */
@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        String password = "123456";
        String encode = bCryptPasswordEncoder.encode(password); // 加密
        System.out.println("123456加密后："+encode); // $2a$10$3iKtIffnixBYG7GwA/reNeiV7/ZXY2zQigzyr3mTHWTebULOCs7OG

        boolean result = bCryptPasswordEncoder.matches(password, encode);
        System.out.println("密码是否匹配："+result);

    }

//    @Test
//    public void test2() {
//        String key = "test";
//        String value = "这个东西是什么";
//        // 设置缓存
//        redisUtil.setCacheObject(key, value);
//        // 获取缓存
//        String cacheObject = redisUtil.getCacheObject(key);
//        System.out.println("缓存中的值："+cacheObject);
//    }
}
