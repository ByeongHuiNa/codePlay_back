package com.codeplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.mapper.userLeave.UserLeaveMapper;
import com.codeplay.service.userLeave.UserLeaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
@Tag(name = "사용자 근태현황에서 휴가관리", description = "사용자 휴가 관리에 필요한 API")
@RestController
@Slf4j
public class UserLeaveController {
	@Autowired
	UserLeaveService service;
	
	@Operation(summary = "user 사용자의 휴가보유현황", description = "메인페이지, 근태현황조회페이지에서 휴가보유현황을볼때 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave")
	public LeaveVo getLeave(@RequestParam int user_no) {
		
		return service.getLeave(user_no);
		
		
	}
	@Operation(summary = "user 사용자의 휴가신청내역", description = "근태현황페이지 휴가탭, 사용자 휴가페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-request")
	public Leave_ApprovalVo getLeaveRequest(@RequestParam int user_no) {
		
		return service.getLeaveRequest(user_no);
	}
	
	
	

}
