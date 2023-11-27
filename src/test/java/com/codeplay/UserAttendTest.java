package com.codeplay;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.service.userAttend.UserAttendService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest

class UserAttendTest {

	
	@Autowired
	UserAttendService userAttendService;
	
	@Test
	@DisplayName("사용자의 전체 근태내역 테스트")
	void test1() {
		log.info("사용자의 전체 근태내역 테스트: "+ userAttendService.getAttendByUserNo(1).toString());
	}
	
	@Test
	@DisplayName("사용자의 오늘 근태내역 테스트")
	void test2() {
		log.info("사용자의 오늘 근태내역 테스트: " + userAttendService.getTodayByUserNo(1).toString());
	}
	
	@Test
	@DisplayName("사용자의 11월 근태내역 테스트")
	void test3() {
		log.info("사용자의 11월 근태내역 테스트: " + userAttendService.getAttendByUserNoMonth(1, 11).toString());
	}
	
	@Test
	@DisplayName("사용자의 출근기록 테스트")
	void test4() {
		AttendanceVo atvo = new AttendanceVo();
		log.info("사용자의 출근기록 테스트: " + userAttendService.saveStartAttendance(1, atvo));
	}
	
	@Test
	@DisplayName("사용자의 퇴근기록 테스트")
	void test5() {
		AttendanceVo atvo = new AttendanceVo();
		log.info("사용자의 퇴근기록 테스트: " + userAttendService.saveEndAttendance(1, atvo));
	}
	@Test
	@DisplayName("사용자의 주간근무시간(정상근무) 테스트")
	void test6() {
		AttendanceVo atvo = new AttendanceVo();
		log.info("사용자의 주간근무시간(정상근무) 테스트: " + userAttendService.getUserTotalAttend(1));
	}
	@Test
	@DisplayName("사용자의 주간근무시간(휴가) 테스트")
	void test7() {
		
		log.info("사용자의 주간근무시간(휴가) 테스트: " + userAttendService.getUserAttendLeaveTotal(1));
	}
	@Test
	@DisplayName("사용자의 주간근무시간(초과근무) 테스트")
	void test8() {
		
		log.info("사용자의 주간근무시간(초과근무) 테스트: " + userAttendService.getUserAttendOverTotal(1));
	}
//	@Test
//	@DisplayName("부서별 사원들의 근태현황(주별) 테스트")
//	void test9() {
//		
//		log.info("부서별 사원들의 근태현황(주별) 테스트: " + userAttendService. getUsersAttendWeek(1, "월요일"));
//	}
	@Test
	@DisplayName("사용자의  테스트")
	void test9() {
		
		log.info("사용자의 주간근무시간(초과근무) 테스트: " + userAttendService.getUserPolicy(1));
	}


}
