package com.codeplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.service.managerApproval.ManagerApprovalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "근태담당자 결재 관리", description = "근태담당자 결재 관리에 필요한 API")
@RestController
@Slf4j
public class ManagerApprovalController {
	@Autowired
	ManagerApprovalService managerApprovalService;
	
	@Operation(summary = "근태담당자의 휴가 결재 리스트", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-leave-approval")
	public List<ApprovalLeaveResponseVo> getLeaveApproval(@RequestParam int user_no){
		return managerApprovalService.getLeaveApprovalByUserNo(user_no);
	}
	
	@Operation(summary = "근태담당자의 출퇴근 결재 리스트", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-attend-approval")
	public List<ApprovalAttendResponseVo> getAttendApproval(@RequestParam int user_no){
		return managerApprovalService.getAttendApprovalByUserNo(user_no);
	}
	
	@Operation(summary = "근태담당자의 휴가 결재 처리", description = "근태담당자 결재 페이지에서 사용")
	@PatchMapping("/manager-leave-approval")
	public void updateLeaveApproval(@RequestBody ApprovalRequestVo vo) {
		if(vo.getLeaveappln_order() == 1) { // 1차결재
			managerApprovalService.updateFirstLeaveApproval(vo);
		} else if(vo.getLeaveappln_order() == 2) { // 2차결재
			managerApprovalService.updateSecondLeaveApproval(vo);
		}
	}
}
