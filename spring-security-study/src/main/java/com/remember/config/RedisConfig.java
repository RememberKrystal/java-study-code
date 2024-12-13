package com.remember.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
 * @Author      : RememberKrystal
 * @Date        : 2024/7/28 15:09
 * @Description : Redis配置类
 */
@Configuration
public class RedisConfig {

    /**
     * 创建RedisTemplate bean，用于操作Redis数据库。
     * 该方法配置了Redis模板的序列化方式，包括键的序列化和值的序列化。
     *
     * @param redisConnectionFactory Redis连接工厂，用于创建Redis连接。
     * @return RedisTemplate实例，配置了字符串序列化器用于键和值的序列化。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 Redis 连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        // 设置键的序列化方式为 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());

        // 创建 Jackson2JsonRedisSerializer 实例，并设置 ObjectMapper
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 配置 Jackson ObjectMapper，支持 Java 8 时间类型（如 LocalDateTime）
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 注册 JavaTimeModule 支持 LocalDateTime 类型
        serializer.setObjectMapper(objectMapper);

        // 设置值的序列化方式为 Jackson2JsonRedisSerializer
        template.setValueSerializer(serializer);

        // 设置 hash 键的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置 hash 值的序列化方式
        template.setHashValueSerializer(serializer);

        return template;
    }

}
