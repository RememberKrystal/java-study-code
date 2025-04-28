package com.remember.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/2 21:20
 * @Description : 公共配置类
 */
@Configuration
public class CommonConfiguration {
    @Bean
    public ChatClient chatClient(OllamaChatModel model) {
        return ChatClient
                .builder(model)
                .defaultSystem("你的名字叫智能客服，主要是用来解答关于岗位的信息") // 设置默认的系统角色
                .defaultAdvisors(new SimpleLoggerAdvisor()) // 设置默认的日志记录器
                .build();
    }
}
