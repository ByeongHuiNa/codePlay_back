package com.codeplay.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.codeplay.domain.AlarmVo;
import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.Attendance_Edit_ApprovalVo;
import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.AttendanceWeekTotalResponseVo;
import com.codeplay.domain.attend.vo.UserAttendEditRequestVo;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;
import com.codeplay.domain.attend.vo.UsersAttendVo;
import com.codeplay.domain.attend.vo.UsersAttendWeekVo;
import com.codeplay.domain.managerApproval.dto.AttendPolicyDto;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.alarm.AlarmService;
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
	
	@Autowired
	AlarmService alarmService;
	
	@Operation(summary = "사용자의 출/퇴근 내역", description = "근태현황조회, 출퇴근 수정 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend")
	public ResponseEntity<Object> getAttendance(@RequestParam int user_no, @RequestHeader("Authorization") String token) {
		log.info("User's Attendance List / user_ no : " + user_no);
        if(TokenUtils.getPageListFromToken(token.substring(6)).contains(4)){
            log.info("4번 페이지 권한(사용자 검색 페이지)이 있습니다.");
        } else {
        	return ResponseEntity.status(403).build();
        }
		return ResponseEntity.ok(userAttendService.getAttendByUserNo(user_no));
	}
	
	@Operation(summary = "사용자의 이상 출퇴근 내역", description = "사용자 출퇴근 수정 페이지")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-wrong-attend")
	public List<AttendanceVo> getWrongAttend(@RequestParam int user_no) {
		return userAttendService.getWrongAttendByUserNo(user_no);
	}
	
	@Operation(summary = "사용자의 월별 근태 내역", description = "근태현황조회, 출퇴근 수정 페이지에서 사용")
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
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.", Locale.KOREA);
	    LocalDate localDate = LocalDate.parse(date, formatter);
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
	
	@Operation(summary = "사용자의 출/퇴근 수정 내역 삭제", description = "출퇴근 수정 페이지에서 사용")
	@Parameter(name = "attendapp_no", description = "출퇴근 수정 내역을 식별하기위한 결재번호")
	@GetMapping("/attend-edit-delete")
	public int deleteAttendRequest(@RequestParam int attendapp_no) {
		return userAttendService.deleteAttendRequestByAppNo(attendapp_no);
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
		int data_no = userAttendService.addAttendEdit(dto);
		log.info(data_no + " ");
		AlarmVo alarm = new AlarmVo();
		alarm.setUser_no(vo.getAttendapp_user_no());
		alarm.setAlarm_content("출퇴근 수정 요청");
		alarm.setGo_to_url("/approvalattendance");
		alarm.setAlarm_kind(0);
		alarm.setAlarm_send_user_no(user_no);
		alarm.setAlarm_data_no(data_no);
		alarm.setAlarm_index(1);
		alarmService.addAlarm(alarm);
		return data_no;
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
//		log.info("출근기록: " + userAttendService.saveStartAttendance(user_no, atvo));
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
	
	@Operation(summary = "사용자의 주간 근무시간 조회(휴가)", description = "메인페이지, 근태현황페이지 출퇴근탭에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend-total-leave")
	public List<AttendanceVo> getUserTotalAttendLeave(@RequestParam int user_no) {
		return userAttendService.getUserAttendLeaveTotal(user_no);
	}
	
	@Operation(summary = "사용자의 주간 근무시간 조회(초과근무)", description = "메인페이지, 근태현황페이지 출퇴근탭에서 사용")
	@Parameter(name = "user_no", description = "유저를 식별하기 위한 유저번호")
	@GetMapping("/user-attend-total-over")
	public List<AttendanceVo> getUserTotalAttendOver(@RequestParam int user_no) {
		return userAttendService.getUserAttendOverTotal(user_no);
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
	@Parameter(name = "week_monday", description = "해당 주 월요일 날짜")
	@GetMapping("/see-all-attendance-week")
	public List<UsersAttendWeekVo> getUsersAttendByWeek(@RequestParam int dept_no, @RequestParam String week_monday) {
		log.info("부서별 사용자들의 주별 근태: " + userAttendService.getUsersAttendWeek(dept_no,week_monday));
		return userAttendService.getUsersAttendWeek(dept_no, week_monday);
	}
	
	@Operation(summary = "사용자의 주간정규근무시간 합", description = "근태현황조회페이지")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-attend-total-week")
	public AttendanceWeekTotalResponseVo getUserAttendWeek(@RequestParam int user_no) {
		log.info("사용자의 주간근무시간 합: " + userAttendService.getUserAttendWeek(user_no));
		return userAttendService.getUserAttendWeek(user_no);
	}
	
	@Operation(summary = "사용자의 주간초과근무시간 합", description = "근태현황조회페이지")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-attend-total-week-over")
	public AttendanceWeekTotalResponseVo getUserAttendWeekOver(@RequestParam int user_no) {
		log.info("사용자의 주간근무시간 합: " + userAttendService.getUserAttendWeek(user_no));
		return userAttendService.getUserAttendOverWeek(user_no);
	}
	@Operation(summary = "사용자의 현재 출퇴근 정책", description = "사용자 출퇴근 수정 페이지")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-attend-policy")
	public AttendPolicyDto getUserPolicy(@RequestParam int user_no) {
		return userAttendService.getUserPolicy(user_no);
	}
}
