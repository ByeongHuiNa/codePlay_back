package com.codeplay.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.Attendance_Edit_ApprovalVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.UserAttendEditRequestVo;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;
import com.codeplay.domain.attend.vo.UsersAttendVo;
import com.codeplay.service.userAttend.UserAttendService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 출/퇴근 관리", description = "사용자 근태 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
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
	
	@Operation(summary = "사용자의 특정 날짜의 출/퇴근 내역", description = "출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@Parameter(name = "date", description = "조회할 날짜를 식별하기 위한 데이터")
	@GetMapping("/user-attend-date")
	public AttendanceVo getAttendanceDate(@RequestParam int user_no, String date) throws ParseException {
		LocalDate localDate = LocalDate.parse(date);
		log.info(localDate.getYear() + "년");
		log.info(localDate.getMonthValue() + "월");
		log.info(localDate.getDayOfMonth() + "일");
		return userAttendService.getAttendByUserNoDate(user_no, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
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
	public List<UserAttendEditResponseVo> getAttendanceEdit(@RequestParam int user_no) {
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
		dto.setAttendoriginal_start_time(vo.getAttendoriginal_start_time());
		dto.setAttendoriginal_end_time(vo.getAttendoriginal_end_time());
		dto.setAttendoriginal_status(vo.getAttendoriginal_status());
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
	
	@Operation(summary = "사용자가 출근 기록하기", description = "메인페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@PostMapping("/user-attend-today")
	public int addStartAttendance(@RequestParam int user_no, @RequestBody AttendanceVo atvo) throws UnknownHostException {
	
		InetAddress ipAddress = InetAddress.getLocalHost();
		String ip = ipAddress.getHostAddress();		
		log.info("현재아이피 : " + ipAddress.getHostAddress());
		atvo.setUser_no(user_no);
		return userAttendService.saveStartAttendance(user_no, atvo);
	}
	
	@Operation(summary = "사용자가 퇴근 기록하기", description = "메인페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@PatchMapping("/user-attend-today")
	public int addEndAttendance(@RequestParam int user_no, @RequestBody AttendanceVo atvo) {
		atvo.setUser_no(user_no);
		return userAttendService.saveEndAttendance(user_no, atvo);
	}
	
	@Operation(summary = "사용자의 주간 근무시간 조회(일별로)", description = "메인페이지, 근태현황페이지 출퇴근탭에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend-total")
	public List<AttendanceVo> getUserTotalAttend(@RequestParam int user_no) {
		return userAttendService.getUserTotalAttend(user_no);
	}
	
	@Operation(summary = "부서별 사용자들의 일별 근태현황", description = "근태현황조회페이지(담당자)")
	@Parameter(name = "dept_no", description = "부서를 식별하기 위한 부서번호")
	@GetMapping("/see-all-attendance-day")
	public List<UsersAttendVo> getUsersAttendByDay(@RequestParam int dept_no) {
		log.info("부서별 사용자들의 일별 근태: " + userAttendService.getUsersAttendDay(dept_no));
		return userAttendService.getUsersAttendDay(dept_no);
	}
	
	@Operation(summary = "부서별 사용자들의 주별 근태현황", description = "근태현황조회페이지(담당자)")
	@Parameter(name = "dept_no", description = "부서를 식별하기 위한 부서번호")
	@GetMapping("/see-all-attendance-week")
	public List<UsersAttendVo> getUsersAttendByWeek(@RequestParam int dept_no) {
		log.info("부서별 사용자들의 주별 근태: " + userAttendService.getUsersAttendWeek(dept_no));
		return userAttendService.getUsersAttendWeek(dept_no);
	}
}
