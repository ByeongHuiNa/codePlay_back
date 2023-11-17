package com.codeplay.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codeplay.domain.AlarmVo;
import com.codeplay.domain.DeptVo;
import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.calendar.vo.UserLeaveVo;
import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.dto.UserLeaveCancelRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveLineRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveRequestDto;
import com.codeplay.domain.leave.vo.Leave_WaitlVo;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.leave.vo.UserLeaveCancelRequestVo;
import com.codeplay.domain.leave.vo.UserLeaveRequestVo;
import com.codeplay.domain.leave.vo.UserLeaveResponseVo;
import com.codeplay.domain.leave.vo.UsersLeaveCountVo;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
import com.codeplay.mapper.userLeave.UserLeaveMapper;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.alarm.AlarmService;
import com.codeplay.service.userLeave.UserLeaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 휴가 관리", description = "사용자 휴가 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class UserLeaveController {
	@Autowired
	UserLeaveService userLeaveService;
	
	@Autowired
	AlarmService alarmService;
	
	@Operation(summary = "사용자(user)의 휴가 보유현황", description = "메인페이지, 근태현황조회, 휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave")
	public LeaveVo getLeave(@RequestParam int user_no) {
		return userLeaveService.getLeave(user_no);
	}
	
	@Operation(summary = "사용자(user)의 휴가신청 대기내역", description = "근태현황조회에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-wait")
	public List<UserLeaveApprovalLineVo> getLeaveWait(@RequestParam int user_no) throws ParseException {
		List<UserLeaveApprovalLineVo> list = new ArrayList();
		List<UserLeaveApprovalLineDto> leaves = userLeaveService.getUserLeaveWait(user_no);
		log.info("서비스로부터 받아온 데이터: "+leaves);
		for(UserLeaveApprovalLineDto leave : leaves) {
			UserLeaveApprovalLineVo u = new UserLeaveApprovalLineVo();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String fd = leave.getLeaveapp_final_date();
			log.info("서비스로부터 받아: "+fd);
			if(fd == null) {
				u.setLeaveapp_final_date("");
			} else {
				Date fdd = format.parse(fd);
				u.setLeaveapp_final_date(format.format(fdd));
			}
			
			u.setLeaveapp_no(leave.getLeaveapp_no());
			u.setUser_no(leave.getUser_no());
			u.setUser_name(leave.getUser_name());
			u.setLeaveapp_title(leave.getLeaveapp_title());
			u.setLeaveapp_content(leave.getLeaveapp_content());
			u.setLeaveapp_start(format.format(leave.getLeaveapp_start()));
			u.setLeaveapp_end(format.format(leave.getLeaveapp_end()));
			u.setLeaveapp_status(leave.getLeaveapp_status());
			//u.setLeaveapp_final_date(format.format(fdd));
			u.setLeaveapp_type(leave.getLeaveapp_type());
			u.setLeaveapp_total(leave.getLeaveapp_total());
			u.setLeaveapp_req_date(format.format(leave.getLeaveapp_req_date()));
			u.setFirstapp_no(leave.getFirstapp_no());
			u.setFirstapp_user_no(leave.getFirstapp_user_no());
			u.setFirstapp_user_name(leave.getFirstapp_user_name());
			u.setFirstapp_status(leave.getFirstapp_status());
			u.setSecondapp_no(leave.getSecondapp_no());
			u.setSecondapp_user_no(leave.getSecondapp_user_no());
			u.setSecondapp_user_name(leave.getSecondapp_user_name());
			u.setSecondapp_status(leave.getSecondapp_status());
				
			list.add(u);
		}
		return list;
	}
		
	@Operation(summary = "사용자(user)의 휴가 신청내역(진행중, 결재완료)", description = "근태현황조회")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@Parameter(name = "month", description = "몇월인지 식별하기 위한 월데이터")
	@GetMapping("/user-leave-request")
	public List<UserLeaveApprovalLineVo> getLeaveRequest(@RequestParam int user_no, int month) throws ParseException {
		List<UserLeaveApprovalLineVo> list = new ArrayList();
		List<UserLeaveApprovalLineDto> leaves = userLeaveService.getLeaveRequest(user_no, month);
		log.info("서비스로부터 받아온 데이터: "+leaves);
		for(UserLeaveApprovalLineDto leave : leaves) {
			UserLeaveApprovalLineVo u = new UserLeaveApprovalLineVo();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String fd = leave.getLeaveapp_final_date();
			log.info("서비스로부터 받아: "+fd);
			if(fd == null) {
				u.setLeaveapp_final_date("");
			} else {
				Date fdd = format.parse(fd);
				u.setLeaveapp_final_date(format.format(fdd));
			}
			
			u.setLeaveapp_no(leave.getLeaveapp_no());
			u.setUser_no(leave.getUser_no());
			u.setUser_name(leave.getUser_name());
			u.setLeaveapp_title(leave.getLeaveapp_title());
			u.setLeaveapp_content(leave.getLeaveapp_content());
			u.setLeaveapp_start(format.format(leave.getLeaveapp_start()));
			u.setLeaveapp_end(format.format(leave.getLeaveapp_end()));
			u.setLeaveapp_status(leave.getLeaveapp_status());
			//u.setLeaveapp_final_date(format.format(fdd));
			u.setLeaveapp_type(leave.getLeaveapp_type());
			u.setLeaveapp_total(leave.getLeaveapp_total());
			u.setLeaveapp_req_date(format.format(leave.getLeaveapp_req_date()));
			u.setFirstapp_no(leave.getFirstapp_no());
			u.setFirstapp_user_no(leave.getFirstapp_user_no());
			u.setFirstapp_user_name(leave.getFirstapp_user_name());
			u.setFirstapp_status(leave.getFirstapp_status());
			u.setSecondapp_no(leave.getSecondapp_no());
			u.setSecondapp_user_no(leave.getSecondapp_user_no());
			u.setSecondapp_user_name(leave.getSecondapp_user_name());
			u.setSecondapp_status(leave.getSecondapp_status());
				
			list.add(u);
		}
		return list;
	}
	@Operation(summary = "사용자(user)의 최근 휴가 신청내역", description = "근태현황조회")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-request-main")
	public List<UserLeaveApprovalLineVo> getLeaveRecentRequest(@RequestParam int user_no) {
		List<UserLeaveApprovalLineVo> list = new ArrayList();
		List<UserLeaveApprovalLineDto> leaves = userLeaveService.getUserRecentLeaveRequest(user_no);
		log.info("서비스로부터 받아온 데이터: "+leaves);
		for(UserLeaveApprovalLineDto leave : leaves) {
			UserLeaveApprovalLineVo u = new UserLeaveApprovalLineVo();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			u.setLeaveapp_no(leave.getLeaveapp_no());
			u.setUser_no(leave.getUser_no());
			u.setUser_name(leave.getUser_name());
			u.setLeaveapp_title(leave.getLeaveapp_title());
			u.setLeaveapp_content(leave.getLeaveapp_content(	));
			u.setLeaveapp_start(format.format(leave.getLeaveapp_start()));
			u.setLeaveapp_end(format.format(leave.getLeaveapp_end()));
			u.setLeaveapp_status(leave.getLeaveapp_status());
			//u.setLeaveapp_final_date(format.format(leave.getLeaveapp_final_date()));
			u.setLeaveapp_type(leave.getLeaveapp_type());
			u.setLeaveapp_total(leave.getLeaveapp_total());
			u.setLeaveapp_req_date(format.format(leave.getLeaveapp_req_date()));
			u.setFirstapp_no(leave.getFirstapp_no());
			u.setFirstapp_user_no(leave.getFirstapp_user_no());
			u.setFirstapp_user_name(leave.getFirstapp_user_name());
			u.setFirstapp_status(leave.getFirstapp_status());
			u.setSecondapp_no(leave.getSecondapp_no());
			u.setSecondapp_user_no(leave.getSecondapp_user_no());
			u.setSecondapp_user_name(leave.getSecondapp_user_name());
			u.setSecondapp_status(leave.getSecondapp_status());
				
			list.add(u);
		}
		return list;
	}
	
	@Operation(summary = "사용자(user)의 모든 휴가 신청 내역 [결재대기]", description = "휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-request-await")
	public ResponseEntity<Object> getAwaitLeaveRequest(@RequestParam int user_no, @RequestHeader("Authorization") String token) {
        if(TokenUtils.getPageListFromToken(token.substring(6)).contains(5)){
            log.info("5번 페이지 권한(사용자 검색페이지)이 있습니다.");
        } else {
        	return ResponseEntity.status(403).build();
        }
		return ResponseEntity.ok(userLeaveService.getAwaitLeaveRequestByUserNo(user_no));
	}
	
	@Operation(summary = "사용자(user)의 모든 휴가 신청 내역 [승인,반려,결재진행중]", description = "휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-leave-request-recent")
	public List<UserLeaveResponseVo> getRecentLeaveRequest(@RequestParam int user_no) {
		return userLeaveService.getRecentLeaveRequestByUserNo(user_no);
	}	
	
	@Operation(summary = "사용자(user)의 결재 대기 상태인 신청 휴가 취소", description = "휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@DeleteMapping("/user-leave-request-await")
	public int removeLeaveRequest(@RequestParam int leaveapp_no) {
		return userLeaveService.removeLeaveRequestByAppNo(leaveapp_no);
	}	
	
	@Operation(summary = "사용자(user)의 휴가 신청", description = "휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@PostMapping("/user-leave-request")
	public void addLeaveRequest(@RequestParam int user_no, @RequestBody UserLeaveRequestVo vo) {
		log.info("User's Leave Request / user_no : " + user_no + "vo : " + vo);
		UserLeaveRequestDto dto = new UserLeaveRequestDto();
		UserLeaveLineRequestDto dtoFirstLine = new UserLeaveLineRequestDto();
		UserLeaveLineRequestDto dtoSecondLine = new UserLeaveLineRequestDto();
		dto.setUser_no(user_no);
		dto.setLeaveapp_title(vo.getLeaveapp_title());
		dto.setLeaveapp_content(vo.getLeaveapp_content());
		dto.setLeaveapp_start(vo.getLeaveapp_start());
		dto.setLeaveapp_end(vo.getLeaveapp_end());
		dto.setLeaveapp_total(vo.getLeaveapp_total());
		dto.setLeaveapp_type(vo.getLeaveapp_type());
		dtoFirstLine.setUser_no(vo.getFirstapp_no()); 
		dtoFirstLine.setOrder(1);
		dtoSecondLine.setUser_no(vo.getSecondapp_no());
		dtoSecondLine.setOrder(2);
		int data_no = userLeaveService.addLeaveRequest(dto, dtoFirstLine, dtoSecondLine);
		AlarmVo alarm = new AlarmVo();
		alarm.setUser_no(vo.getFirstapp_no());
		alarm.setAlarm_content("휴가 신청 1차 결재 요청");
		alarm.setGo_to_url("/approvalattendance");
		alarm.setAlarm_kind(0);
		alarm.setAlarm_send_user_no(user_no);
		alarm.setAlarm_index(0);
		alarm.setAlarm_data_no(data_no);
		alarmService.addAlarm(alarm);
	}
	
	@Operation(summary = "사용자(user)의 휴가 취소 신청", description = "휴가신청 페이지에서 사용")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@PostMapping("/user-leave-cancel-request")
	public void addLeaveCancelRequest(@RequestParam int user_no, @RequestBody UserLeaveCancelRequestVo vo) {
		log.info("User's Leave Cancel Request / user_no : " + user_no + "vo : " + vo);
		UserLeaveCancelRequestDto dto = new UserLeaveCancelRequestDto();
		UserLeaveLineRequestDto dtoFirstLine = new UserLeaveLineRequestDto();
		dto.setUser_no(user_no);
		dto.setLeaveapp_title(vo.getLeaveapp_title());
		dto.setLeaveapp_content(vo.getLeaveapp_content());
		dto.setLeaveapp_start(vo.getLeaveapp_start());
		dto.setLeaveapp_end(vo.getLeaveapp_end());
		dto.setLeaveapp_total(vo.getLeaveapp_total());
		dto.setLeaveapp_type(4);
		dto.setLeaveapp_cancel_no(vo.getLeaveapp_cancel_no());
		dtoFirstLine.setUser_no(vo.getFirstapp_no());
		dtoFirstLine.setOrder(1);
		userLeaveService.addLeaveCancelRequest(dto, dtoFirstLine);
	}	
	@Operation(summary = "근태담당자가 사용자들의 휴가 보유현황 조회", description = "근태현황조회(담당자) 페이지에서 사용")
	@Parameter(name = "dept_no", description = "부서를 식별하기위한 부서번호")
	@GetMapping("/see-all-leave")
	public List<UsersLeaveCountVo> getUsersLeave(@RequestParam int dept_no) {
		log.info("근태담당자가 사용자의 휴가 보유현황 조회 : " + userLeaveService.getUsersLeave(dept_no));
		return userLeaveService.getUsersLeave(dept_no);
	}
	
	@Operation(summary = "부서목록", description = "근태현황조회(담당자) 페이지에서 사용")
	@Parameter(name = "dept_no", description = "부서를 식별하기위한 부서번호")
	@GetMapping("/see-all-dept")
	public List<DeptVo> getDepts() {
		return userLeaveService.getDept();
	}
}
