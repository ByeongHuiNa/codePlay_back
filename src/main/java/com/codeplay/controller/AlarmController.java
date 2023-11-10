package com.codeplay.controller;

import com.codeplay.service.access.AccessService;
import com.codeplay.service.alarm.AlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/api")
@Slf4j
public class AlarmController {
    @Autowired
    AlarmService service;
    @Operation(summary = "alarm 연결", description = "alarm 을 받기위한 sseEmitter 객체 생성 및 연결")
    @Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
    @GetMapping(value = "/alarm", produces = "text/event-stream")
    public ResponseEntity<SseEmitter> sseConnection(@RequestParam int user_no) {
        log.info("alarm 호출, 호출한 user_no : {}", user_no);
        SseEmitter sseEmitter = service.init(user_no);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CACHE_CONTROL, "no-transform");
        headers.add("X-Accel-Buffering", "no");
        return ResponseEntity.ok().headers(headers).body(sseEmitter);
    }
    @Operation(summary = "alarm 기존값 전송", description = "alarm db에 있는 내용 전송")
    @GetMapping("/get-alarm")
    @Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
    public ResponseEntity<Object> GetAlarm(@RequestParam int user_no) {
        log.info("get-alarm 호출, 호출한 user_no : {}", user_no);
        service.getAlarm(user_no);
        return ResponseEntity.ok("초기데이터 호출성공");
    }
}