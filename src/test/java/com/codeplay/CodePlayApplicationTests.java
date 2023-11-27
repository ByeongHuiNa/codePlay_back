package com.codeplay;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.mapper.managerApproval.ManagerApprovalMapper;
import com.codeplay.mapper.userAttend.UserAttendEditMapper;
import com.codeplay.mapper.userAttend.UserAttendMapper;
import com.codeplay.service.userAttend.UserAttendService;
import com.codeplay.service.userLeave.UserLeaveService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class CodePlayApplicationTests {
	
	@Autowired
	UserAttendService userAttendService;
	
	
	@Test
	@DisplayName("")
	void test1() {
		
		log.info("");
		
	}
	
	
}
