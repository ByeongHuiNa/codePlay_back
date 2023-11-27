package com.codeplay;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.domain.managerApproval.vo.DeptLeaveRequestVo;
import com.codeplay.service.managerApproval.ManagerApprovalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ApprovalTest {

	@Autowired
	ManagerApprovalService approvalService;
	
	@Test
	@DisplayName("근태담당자의 휴가 결재 목록 테스트")
	void getLeaveApprovalByUserNo() {
		log.info(approvalService.getLeaveApprovalByUserNo(5).toString());
	}
	
	@Test
	@DisplayName("근태담당자의 출퇴근 수정 결재 목록 테스트")
	void getAttendApprovalByUserNo() {
		log.info(approvalService.getAttendApprovalByUserNo(5).toString());
	}
	
	@Test
	@DisplayName("근태담당자의 초과근무 결재 목록 테스트")
	void getOvertimeApprovalByUserNo() {
		log.info(approvalService.getOvertimeApprovalByUserNo(5).toString());
	}
	
	@Test
	@DisplayName("근태담당자의 휴가 결재 처리 1차 테스트")
	void updateFirstLeaveApproval() {
		ApprovalRequestVo vo = new ApprovalRequestVo(1, 108,  
				 new Date(), new Date(), 2.0, 0, 177, 1, 0, "승인", null);
		approvalService.updateFirstLeaveApproval(vo);
	}
		
	@Test
	@DisplayName("근태담당자의 휴가 결재 처리 2차 테스트")
	void updateSecondLeaveApproval() {
		LocalDateTime localDateTimeStart = LocalDateTime.parse("2023-11-20 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Date start = Date.from(localDateTimeStart.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime localDateTimeEnd = LocalDateTime.parse("2023-11-21 00:00:00.000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		Date end = Date.from(localDateTimeEnd.atZone(ZoneId.systemDefault()).toInstant());
		ApprovalRequestVo vo = new ApprovalRequestVo(1, 108,  
				start, end, 2.0, 0, 178, 2, 0, "승인", null);
		approvalService.updateSecondLeaveApproval(vo);
	}

}
