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
import com.codeplay.service.userLeave.UserLeaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest

class UserLeaveTest {

	
	@Autowired
	UserLeaveService userLeaveService;
	
	@Test
	@DisplayName("사용자의 휴가 보유현황 조회 테스트")
	void test0() {
		log.info("사용자의 휴가 보유현황 조회 테스트: "+ userLeaveService.getLeave(1).toString());
	}
	
	@Test
	@DisplayName("사용자의 휴가 신청내역(대기상태) 테스트")
	void test1() {
		log.info("사용자의 휴가 신청내역(대기상태) 테스트: "+ userLeaveService.getUserLeaveWait(1).toString());
	}
	
	@Test
	@DisplayName("사용자의 월별 휴가 신청내역(결재진행중, 결재완료-승인, 결재완료-반려 상태) 테스트")
	void test2() {
		log.info("사용자의 월별 휴가 신청내역(결재진행중, 결재완료-승인, 결재완료-반려 상태) 테스트: " + userLeaveService.getLeaveRequest(1, 11).toString());
	}
	
	@Test
	@DisplayName("사용자의 최근 휴가 신청내역 테스트")
	void test3() {
		log.info("사용자의 최근 휴가 신청내역 테스트: " + userLeaveService.getUserRecentLeaveRequest(1).toString());
	}
	
	@Test
	@DisplayName("근태담당자의 부서 사원 휴가 현황 조회 테스트")
	void test4() {
		AttendanceVo atvo = new AttendanceVo();
		log.info("근태담당자의 부서 사원 휴가 현황 조회 테스트: " + userLeaveService.getUsersLeave(1));
	}
	
	
	


}
