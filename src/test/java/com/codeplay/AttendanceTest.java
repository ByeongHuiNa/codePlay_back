package com.codeplay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.service.userAttend.UserAttendService;
import com.codeplay.service.userLeave.UserLeaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class AttendanceTest {
	
	@Autowired
	UserAttendService attendService;
	
	@Test
	@DisplayName("같은 부서 근태담당자 내역 테스트")
	void GetManagerByDeptNo() {
		log.info(attendService.getManagerByDeptNo(1).toString());
	}

	@Test
	@DisplayName("출퇴근 수정 내역 테스트")
	void getAttendEditByUserNo() {
		log.info(attendService.getAttendEditByUserNo(1).toString());
	}
	
	@Test
	@DisplayName("출퇴근 수정 내역 취소 테스트")
	void deleteAttendRequestByAppNo() {
		log.info(attendService.deleteAttendRequestByAppNo(57) + " ");
	}
}
