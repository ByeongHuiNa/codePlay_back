package com.codeplay.service.userAttend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.mapper.userAttend.UserAttendMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@Service
public class UserAttendAutoImpl implements UserAttendAuto {
	@Autowired
	UserAttendMapper userAttendMapper;
	
	@Scheduled(cron = "00 00 19 * * *") // 매일 저녁 7시
	@Override
	public void autoInsert() {
		log.info("제발들어가라");
		AttendanceVo atvo = new AttendanceVo();
		userAttendMapper.autoInsert(atvo);
	}

}
