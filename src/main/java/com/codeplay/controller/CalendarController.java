package com.codeplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.service.calendar.CalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "사용자 캘린더 기능", description = "사용자 캘린더 관리에 필요한 API")
@RestController
public class CalendarController {
//	@Autowired
//	CalendarService service;
//	
//	@Operation(summary = "user 사용자의 휴가보유현황", description = "메인페이지, 근태현황조회페이지에서 휴가보유현황을볼때 사용")
//	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
//	@GetMapping("/user-leave")
//	public LeaveVo getLeave(@RequestParam int user_no) {
//		
//		return service.getLeave(user_no);
//		
//		
//	}	

}
