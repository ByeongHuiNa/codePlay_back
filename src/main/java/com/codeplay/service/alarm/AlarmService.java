package com.codeplay.service.alarm;

import com.codeplay.domain.AlarmVo;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlarmService {
    public SseEmitter init(int user_no);
    public void getAlarm(int user_no);
    public void addAlarm(AlarmVo alarmVo);
    public void deleteAlarm(int alarmNo);
    public void statusUpdateAlarm(int alarmNo);

}
