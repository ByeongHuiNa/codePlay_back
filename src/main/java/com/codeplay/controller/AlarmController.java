package com.codeplay.controller;

import com.codeplay.domain.AlarmVo;
import com.codeplay.service.access.AccessService;
import com.codeplay.service.alarm.AlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "alarm stuts 변경", description = "alarm 목록 클릭시")
    @Parameter(name = "alarm_no", description = "유저 개인을 식별하기위한 유저번호")
    @PutMapping(value = "/alarm")
    public ResponseEntity<Object> PutAlarm(@RequestParam int alarm_no) {
        log.info("alarm Status 변경, 호출한 alarmVo : {}", alarm_no);
        service.statusUpdateAlarm(alarm_no);
        return ResponseEntity.ok("변경완료");
    }
    @Operation(summary = "alarm 삭제", description = "alarm 목록의 x 클릭시")
    @Parameter(name = "alarm_no", description = "유저 개인을 식별하기위한 유저번호")
    @PutMapping(value = "/alarm-delete")
    public ResponseEntity<Object> DeleteAlarm(@RequestParam int alarm_no) {
        log.info("alarm Status 변경, 호출한 alarmVo : {}", alarm_no);
        service.deleteAlarm(alarm_no);
        return ResponseEntity.ok("삭제완료");
    }
}