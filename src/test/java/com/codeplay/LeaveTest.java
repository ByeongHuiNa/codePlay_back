package com.codeplay;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.domain.leave.dto.UserLeaveLineRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveRequestDto;
import com.codeplay.service.userLeave.UserLeaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class LeaveTest {

	@Autowired
	UserLeaveService leaveService;
	
	@Test
	@DisplayName("사용자의 대기중인 휴가 내역 테스트")
	void getAwaitLeaveRequestByUserNo() {
		log.info(leaveService.getAwaitLeaveRequestByUserNo(5).toString());
	}
	
	@Test
	@DisplayName("사용자의 결재진행중,결재완료 된 휴가 내역 테스트")
	void getRecentLeaveRequestByUserNo() {
		log.info(leaveService.getRecentLeaveRequestByUserNo(5).toString());
	}

	@Test
	@DisplayName("사용자의 휴가 신청 테스트")
	void addLeaveRequest() {
		LocalDateTime localDateTimeStart = LocalDateTime.parse("2023-11-20 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Date start = Date.from(localDateTimeStart.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTimeEnd = LocalDateTime.parse("2023-11-21 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Date end = Date.from(localDateTimeEnd.atZone(ZoneId.systemDefault()).toInstant());
		UserLeaveRequestDto dto = new UserLeaveRequestDto(1, "a", "b", start, end, 2.0, 0);
		UserLeaveLineRequestDto dtoFirstLine = new UserLeaveLineRequestDto(0, 5, 1);
		UserLeaveLineRequestDto dtoSecondLine = new UserLeaveLineRequestDto(0, 6, 2);
		log.info(leaveService.addLeaveRequest(dto, dtoFirstLine, dtoSecondLine) + " ");
	}
	
	
	@Test
	@DisplayName("사용자의 결재대기 중인 휴가 취소 처리 테스트")
	void removeLeaveRequestByAppNo() {
		leaveService.removeLeaveRequestByAppNo(106);
	}
	
		@Test
		@DisplayName("사용자의 결재완료 된 휴가 취소 신청 테스트")
		void addLeaveReques() {
			log.info("z");
	}
}
