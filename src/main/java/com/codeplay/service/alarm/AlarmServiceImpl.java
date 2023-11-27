package com.codeplay.service.alarm;

import com.codeplay.domain.AlarmVo;
import com.codeplay.mapper.alarm.AlarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    HashMap<Integer, SseEmitter> sseEmitters = new HashMap<>();

    public SseEmitter getSseEmitter(int user_no) {
        return sseEmitters.get(user_no);
    }

    /*
        알림 서비스 처음 연결시 실행되는 서비스
        sseEmitters라고하는 HashMap에 유저별로 새sseEmitter를 만들어줌.
     */
    @Override
    public SseEmitter init(int user_no) {
        log.info("sseEmitter init");
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitters.put(user_no, sseEmitter);
        sseEmitter.onTimeout(() -> {
            sseEmitter.complete();
            sseEmitters.remove(user_no);
        });
        try {
            sseEmitter.send(SseEmitter.event().id("0").data("sse connected").name("message"));
            getAlarm(user_no);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
        return sseEmitter;
    }

    /*
        db에 있는 알림 sseEmitter를 이용해서 전송.
     */
    @Override
    public void getAlarm(int user_no) {
        SseEmitter sseEmitter = getSseEmitter(user_no);
        List<AlarmVo> list = alarmMapper.findAll(user_no);
        for (AlarmVo vo : list) {
            HashMap<String, Object> data = AlarmVo2DataHashMap(vo);
            try {
                sseEmitter.send(SseEmitter.event().id(vo.getAlarm_no().toString()).data(data));
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        }
    }

    /*
        새로운 알람 생성시 db에 데이터 생성 및 sseEmitter를 이용해서 전송.
        현재 접속중인 사람이 없다면 null 값으로 return 받음.
     */
    @Override
    public void addAlarm(AlarmVo vo) {
        vo = alarmMapper.insert(vo);
        try {
            SseEmitter sseEmitter = getSseEmitter(vo.getUser_no());
            HashMap<String, Object> data = AlarmVo2DataHashMap(vo);
            sseEmitter.send(SseEmitter.event().id(vo.getAlarm_no().toString()).data(data));
        } catch (IOException e) {
            SseEmitter sseEmitter = getSseEmitter(vo.getUser_no());
            sseEmitter.completeWithError(e);
        } catch (NullPointerException e){
            log.info("알림대상자가 접속해있지 않습니다.");
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

    public HashMap<String, Object> AlarmVo2DataHashMap(AlarmVo vo){
        log.info("가져온 알람 : {}", vo);
        HashMap<String, Object> data = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        data.put("content", vo.getAlarm_content());
        data.put("date", dateFormat.format(vo.getAlarm_date()));
        data.put("status", vo.getAlarm_status());
        data.put("kind", vo.getAlarm_kind());
        data.put("alarm_index", vo.getAlarm_index());
        data.put("alarm_data_no", vo.getAlarm_data_no());
        data.put("alarm_no", vo.getAlarm_no());
        data.put("go_to_url", vo.getGo_to_url());
        data.put("alarm_send_user_no", vo.getAlarm_send_user_no());
        return data;
    }

}
