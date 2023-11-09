package com.codeplay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/sse")
@Slf4j
public class SseController {

    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping("/sse") //발행
    public SseEmitter streamDateTime() { // webmvc
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        log.info("처음 등록된 emitter 주소"+ sseEmitter.toString());
        //TODO: 알림 서비스 추가 예정
//        serversentService.register(sseEmitter);

        return sseEmitter;
    }
}