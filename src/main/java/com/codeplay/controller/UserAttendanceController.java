package com.codeplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.Attendance_Edit_ApprovalVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.userAttend.dto.UserAttendEditDto;
import com.codeplay.domain.userAttend.vo.UserAttendEditRequestVo;
import com.codeplay.service.userAttend.UserAttendService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 출/퇴근 관리", description = "사용자 근태 관리에 필요한 API")
@RestController
@Slf4j
public class UserAttendanceController {
	@Autowired
	UserAttendService userAttendService;
	
	@Operation(summary = "사용자의 출/퇴근 내역", description = "근태현황조회, 출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend")
	public List<AttendanceVo> getAttendance(@RequestParam int user_no) {
		log.info("User's Attendance List / user_ no : " + user_no);
		return userAttendService.getAttendByUserNo(user_no);
	}
	
	@Operation(summary = "사용자의 월별 출/퇴근 내역", description = "근태현황조회, 출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@Parameter(name = "month", description = "몇월인지 식별하기 위한 월데이터")
	@GetMapping("/user-attend-month")
	public List<AttendanceVo> getAttendanceMonth(@RequestParam int user_no, int month) {
		
		return userAttendService.getAttendByUserNoMonth(user_no, month);
	}
	
	@Operation(summary = "사용자가 속한 부서의 근태담당자 내역", description = "출퇴근 수정 페이지에서 사용")
	@Parameter(name = "dept_no", description = "부서를 식별하기 위한 부서번호")
	@GetMapping("/dept-manager")
	public List<UserVo> getManager(@RequestParam int dept_no) {
		log.info("Dept Manager List / dept_no : " + dept_no);
		return userAttendService.getManagerByDeptNo(dept_no);
	}
	
	@Operation(summary = "사용자의 출/퇴근 수정 내역", description = "출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/attend-edit")
	public List<Attendance_Edit_ApprovalVo> getAttendanceEdit(@RequestParam int user_no) {
		log.info("User's Attendance Edit List / user_no : " + user_no);
		return userAttendService.getAttendEditByUserNo(user_no);
	}
	
	@Operation(summary = "사용자의 출/퇴근 수정", description = "출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@PostMapping("/attend-edit")
	public int addAttendEdit(@RequestParam int user_no, @RequestParam int attend_no, @RequestBody UserAttendEditRequestVo vo) {
		log.info("User's Attendance Edit / user_no : " + user_no + "attend_no : " + attend_no + "vo : " + vo);
		UserAttendEditDto dto = new UserAttendEditDto();
		dto.setUser_no(user_no);
		dto.setAttend_no(attend_no);
		dto.setAttendedit_title(vo.getAttendedit_title());
		dto.setAttendedit_reason(vo.getAttendedit_reason());
		dto.setAttendedit_kind(vo.getAttendedit_kind());
		dto.setAttendedit_start_time(vo.getAttendedit_start_time());
		dto.setAttendedit_end_time(vo.getAttendedit_end_time());
		dto.setAttendapp_user_no(vo.getAttendapp_user_no());
		dto.setAttendapp_status(2);
		return userAttendService.addAttendEdit(dto);
	}
	
	@Operation(summary = "사용자의 오늘 출/퇴근 내역", description = "메인페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend-today")
	public List<AttendanceVo> getAttendanceToday(@RequestParam int user_no) {
		
		return userAttendService.getTodayByUserNo(user_no);
	}
	
	@Operation(summary = "사용자가 출퇴근 기록하기", description = "메인페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@PostMapping("/user-attend-today")
	public int addAttendance(@RequestParam int user_no, @RequestBody AttendanceVo atvo) {

		atvo.setUser_no(user_no);
		return userAttendService.saveAttendance(atvo);
	}
}
