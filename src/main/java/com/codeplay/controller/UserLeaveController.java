package com.codeplay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
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
	UserLeaveService userLeaveService;

	@Operation(summary = "user 사용자의 휴가보유현황", description = "메인페이지, 근태현황조회페이지에서 휴가보유현황을볼때 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave")
	public LeaveVo getLeave(@RequestParam int user_no) {

		return userLeaveService.getLeave(user_no);

	}
	
	@Operation(summary = "user 사용자의 휴가신청내역", description = "근태현황페이지 휴가탭, 사용자 휴가페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-request")
	public List<UserLeaveApprovalLineVo> getLeaveRequest(@RequestParam int user_no) {
		
		List<UserLeaveApprovalLineVo> list = new ArrayList();
		List<UserLeaveApprovalLineDto> leaves = userLeaveService.getLeaveRequest2(user_no);
		log.info("서비스로부터 받아온 데이터: "+leaves);
		for(UserLeaveApprovalLineDto leave : leaves) {
			UserLeaveApprovalLineVo u = new UserLeaveApprovalLineVo();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			u.setLeaveapp_no(leave.getLeaveapp_no());
			u.setUser_no(leave.getUser_no());
			u.setLeaveapp_title(leave.getLeaveapp_title());
			u.setLeaveapp_content(leave.getLeaveapp_content());
			u.setLeaveapp_start(format.format(leave.getLeaveapp_start()));
			u.setLeaveapp_end(format.format(leave.getLeaveapp_end()));
			//u.setLeaveapp_final_date(format.format(leave.getLeaveapp_final_date()));
			u.setLeaveapp_type(leave.getLeaveapp_type());
			u.setLeaveapp_total(leave.getLeaveapp_total());
			u.setOne(leave.getOne());
			u.setTwo(leave.getTwo());
			u.setLeaveapp_status(leave.getLeaveapp_status());
			list.add(u);
		}
		
		return list;
	}


}
