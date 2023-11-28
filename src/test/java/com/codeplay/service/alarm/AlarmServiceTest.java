package com.codeplay.service.alarm;

import com.codeplay.domain.AlarmVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@Slf4j
class AlarmServiceTest {

    @Autowired
    private AlarmService alarmService;

    @Test
    @DisplayName("알림 서비스 초기실행 테스트")
    void init() {
        int user_no = 1;

        // 테스트할 서비스 호출
        SseEmitter result = alarmService.init(user_no);

        log.info("sseEmitter :"+result);


    }

    @Test
    @DisplayName("알림 서비스 조회 및 호출 테스트")
    void getAlarm() {
        int user_no = 1;
        // 테스트할 서비스 호출
        alarmService.getAlarm(user_no);

    }

    @Test
    @DisplayName("알림 서비스 등록 및 호출 테스트")
    void addAlarm() {
        AlarmVo alarmVo = new AlarmVo();
        alarmVo.setAlarm_no(1);
        alarmVo.setAlarm_content("Test");
        alarmVo.setAlarm_kind(9);
        alarmVo.setUser_no(1);
        alarmVo.setAlarm_send_user_no(1);
        alarmVo.setGo_to_url("test");
        alarmVo.setAlarm_data_no(9);
        alarmVo.setAlarm_index(9);
        // 테스트할 서비스 호출
        alarmService.addAlarm(alarmVo);
    }

    @Test
    @DisplayName("알림 상태 수정 테스트")
    void statusUpdateAlarm() {
        int alarmNo = 1;

        // 테스트할 서비스 호출
        alarmService.statusUpdateAlarm(alarmNo);

    }

    @Test
    @DisplayName("알림 서비스 삭제 테스트")
    void deleteAlarm() {
        int alarmNo = 1;

        // 테스트할 서비스 호출
        alarmService.deleteAlarm(alarmNo);

    }


}