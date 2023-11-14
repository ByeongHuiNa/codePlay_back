package com.codeplay.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalOvertimeResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.domain.managerApproval.vo.DeptLeaveRequestVo;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.managerApproval.ManagerApprovalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "근태담당자 결재 관리", description = "근태담당자 결재 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class ManagerApprovalController {
	@Autowired
	ManagerApprovalService managerApprovalService;
	
	// 근태담당자의 휴가 결재 
	@Operation(summary = "근태담당자의 휴가 결재 리스트", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-leave-approval")
	public ResponseEntity<Object> getLeaveApproval(@RequestParam int user_no, @RequestHeader("Authorization") String token){
        if(TokenUtils.getPageListFromToken(token.substring(6)).contains(8)){
            log.info("8번 페이지 권한(사용자 검색페이지)이 있습니다.");
        } else {
        	return ResponseEntity.status(403).build();
        }
		return ResponseEntity.ok(managerApprovalService.getLeaveApprovalByUserNo(user_no));
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
	
	@Operation(summary = "특정 날짜에 신청자와 같은 부서 사원들의 휴가 사용율", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@PostMapping("/dept-leave")
	public HashMap<Integer, Object> getDeptLeave(@RequestBody DeptLeaveRequestVo vo) {
		return managerApprovalService.getDeptLeaveByUserNo(vo);
	}
	
	// 근태 담당자의 출퇴근 결재 
	@Operation(summary = "근태담당자의 출퇴근 결재 리스트", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-attend-approval")
	public List<ApprovalAttendResponseVo> getAttendApproval(@RequestParam int user_no){
		return managerApprovalService.getAttendApprovalByUserNo(user_no);
	}
	
	@Operation(summary = "근태담당자의 출퇴근 수정 결재 처리", description = "근태담당자 결재 페이지에서 사용")
	@PatchMapping("/manager-attend-approval")
	public void updateAttendApproval(@RequestBody ApprovalAttendRequestVo vo) {
		managerApprovalService.updateAttendApproval(vo);
	}
	
	// 근태 담당자의 초과근무 결재 
	@Operation(summary = "근태담당자의 초과근무 결재 리스트", description = "근태담당자 결재 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-overtime-approval")
	public List<ApprovalOvertimeResponseVo> getOvertimeApproval(@RequestParam int user_no){
		return managerApprovalService.getOvertimeApprovalByUserNo(user_no);
	}
	
//	@Operation(summary = "근태담당자의 초과근무 결재 처리", description = "근태담당자 결재 페이지에서 사용")
//	@PatchMapping("/manager-overtime-approval")
//	public void updateOvertimeApproval(@RequestBody ) {
//	}
}
