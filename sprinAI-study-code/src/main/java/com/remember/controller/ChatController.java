package com.remember.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.concurrent.Future;

/*
 * @Author      : RememberKrystal
 * @Date        : 2025/4/2 21:21
 * @Description :
 */
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient client;

    @GetMapping(value = "/chat")
    public String chat(@RequestParam String prompt) {
        return client.prompt()
                .user(prompt)
                .call()
                .content();
    }

    /**
     * Stream方式输出
     * @param prompt
     * @return
     */
    @GetMapping(value = "/chat-stream",produces = "text/html;charset=utf-8")
    public Flux<String> chatStream(@RequestParam String prompt) {
        return client.prompt()
                .user(prompt)
                .stream()
                .content();
    }
}
