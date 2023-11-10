package com.codeplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.managerModify.vo.UserAttendModifyVo;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.managerModify.ManagerModifyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "근태담당자 근태현황수정", description = "근태담당자가 사용자의 근태 현황을 수정할 때 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class ManagerModifyController {
	@Autowired
	ManagerModifyService managerModifyService;

	@Operation(summary = "근태담당자가 속한 부서의 모든 직원 목록", description = "근태담당자의 근태현황수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/manager-dept-users")
	public ResponseEntity<Object> getAllUsers(@RequestParam int user_no, @RequestHeader("Authorization") String token){
		log.info("All Users List / user_no : " + user_no);
        if(TokenUtils.getPageListFromToken(token.substring(6)).contains(9)){
            log.info("9번 페이지 권한(사용자 검색페이지)이 있습니다.");
        } else {
        	return ResponseEntity.status(403).build();
        }
		return ResponseEntity.ok(managerModifyService.getAllUsersByUserNo(user_no));
	}
	
	@Operation(summary = "근태담당자가 직원의 출퇴근 현황 수정", description = "근태담당자의 근태현황수정 페이지에서 사용")
	@PatchMapping("/manager-attend-modify")
	public void modifyAttend(@RequestBody UserAttendModifyVo vo) {
		managerModifyService.modifyAttend(vo);
	}
}
