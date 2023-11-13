package com.codeplay.service.alarm;

import com.codeplay.domain.AlarmVo;
import com.codeplay.mapper.alarm.AlarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmMapper alarmMapper;

    static SseEmitter sseEmitter;

    //TODO:한명의 user의 알람이 아닌 user_no에 따라 다르게 sseEmiiter를 만들어서 가질수있는 HashMap 으로 만들어야함.(여러명이 서로 다른 sseEmitter를 이용하려면)
    @Override
    public SseEmitter init(int user_no) {
        log.info("sseEmitter init");
        sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onTimeout(() -> {
            sseEmitter.complete();
        });
        try {
            sseEmitter.send(SseEmitter.event().id("0").data("sse connected").name("message"));
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
        return sseEmitter;
    }

    @Override
    public void getAlarm(int user_no) {
        List<AlarmVo> list = alarmMapper.findAll(user_no);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (AlarmVo vo : list) {
            log.info("가져온 알람 : {}", vo);
            HashMap<String, Object> data = new HashMap<>();
            data.put("date", dateFormat.format(vo.getAlarm_date()));
            data.put("status", vo.getAlarm_status());
            data.put("kind", vo.getAlarm_kind());
            data.put("alarm_send_user_no", vo.getAlarm_send_user_no());
            try {
                sseEmitter.send(SseEmitter.event().id(vo.getAlarm_no().toString()).data(data));
                sseEmitter.complete();
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        }
    }

    @Override
    public void addAlarm(AlarmVo alarmVo) {
        alarmMapper.insert(alarmVo);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        data.put("date", dateFormat.format(alarmVo.getAlarm_date()));
        data.put("status", alarmVo.getAlarm_status());
        data.put("kind", alarmVo.getAlarm_kind());
        data.put("alarm_send_user_no", alarmVo.getAlarm_send_user_no());
        try {
            sseEmitter.send(ServerSentEvent.builder()
                    .id(alarmVo.getAlarm_no().toString()).event(alarmVo.getAlarm_content())
                    .data(data)
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteAlarm(int alarmNo) {
        alarmMapper.delete(alarmNo);
    }

    @Override
    public void statusUpdateAlarm(int alarmNo) {
        alarmMapper.updateStatus(alarmNo);
    }

}
