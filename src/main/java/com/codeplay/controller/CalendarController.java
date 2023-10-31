package com.codeplay.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.ScheduleVo;
import com.codeplay.domain.Schedule_MemoVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.calendar.vo.UserLeaveVo;
import com.codeplay.domain.calendar.vo.UserScheduleLeaveMemoVo;
import com.codeplay.domain.calendar.vo.UserScheduleVo;
import com.codeplay.service.calendar.CalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "사용자 캘린더 기능", description = "사용자 캘린더 관리에 필요한 API")
@RestController
public class CalendarController {
	
	@Autowired
	CalendarService service;

	//사용자 일정
		//Calendar에서 user 본인의 사용자 일정을 조회할 때 사용	
		@Operation(summary = "user 사용자 회사/개인 일정 조회", description = "Calendar에서 user 본인의 사용자 회사/개인 일정을 조회할 때 사용")
		@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
		@GetMapping("/user-schedulelist")
		public List<ScheduleVo> getScheduleList(@RequestParam Long user_no) {	
			return service.getScheduleList(user_no);	
		}	
		
		//Calendar에서 user 본인의 사용자 휴가를 조회할 때 사용
		@Operation(summary = "user 사용자 휴가 일정 조회", description = "Calendar에서 user 본인의 사용자 휴가 일정을 조회할 때 사용")
		@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
		@GetMapping("/user-leavelist")
		public List<Leave_ApprovalVo> getLeaveList(@RequestParam Long user_no) {
			return service.getLeaveList(user_no);
		}
		
		//Calendar에서 user 본인의 사용자 일정을 추가할 때 사용
		@Operation(summary = "user 사용자 일정 추가", description = "Calendar에서 user 본인의 사용자 일정을 추가할 때 사용")
		@PostMapping("/user-schedule")
		public void createSchedule(@RequestBody ScheduleVo schedule) {
			service.createSchedule(schedule);
		}
		
		//Calendar에서 user 본인의 사용자 일정을 수정할 때 사용
		@Operation(summary = "user 사용자 일정 수정", description = "Calendar에서 user 본인의 사용자 일정을 수정할 때 사용")
		@PatchMapping("/user-schedule")
		public void updateSchedule(@RequestBody ScheduleVo schedule) {
			service.updateSchedule(schedule);
		}
		
		//Calendar에서 user 본인의 사용자 일정을 삭제할 때 사용
		@Operation(summary = "user 사용자 개인/회사 일정 삭제", description = "Calendar에서 user 본인의 사용자 개인/회사 일정을 삭제할 때 사용")
		@Parameter(name = "schedule_no", description = "일정을 식별하기위한 일정 번호")
		@DeleteMapping("/user-schedule")
		public void deleteSchedule(@RequestParam Long schedule_no) {
			service.deleteSchedule(schedule_no);
		}
		
		
	//사용자 및 사용자 부서의 공유 일정 (사용자, 일정 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서의 공유 일정을 조회할 때 사용
		@Operation(summary = "user 사용자 부서 공유 개인/회사 일정 조회", description = "Calendar에서 user 본인의 사용자 부서 공유 개인/회사 일정을 조회할 때 사용")
		@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
		@GetMapping("/user-dep-schedulelist")
		public List<UserScheduleVo> getShereScheduleList(@RequestParam Long user_no) {
			return service.getShereScheduleList(user_no);
		}
		
		
	//사용자 및 사용자 부서의 공유 휴가 (사용자, 휴가 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서의 휴가 일정을 조회할 때 사용
		@Operation(summary = "user 사용자 부서 공유 휴가 일정 조회", description = "Calendar에서 user 본인의 사용자 부서 공유 휴가 일정을 조회할 때 사용")
		@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
		@GetMapping("/user-dep-leavelist")
		public List<UserLeaveVo> getShereLeaveList(@RequestParam Long user_no) {
			return service.getShereLeaveList(user_no);
		}
		
		
	//사용자 및 사용자 부서의 메모 (사용자, 일정, 휴가, 일정 메모 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (일정을 클릭했을때)
		@Operation(summary = "user 사용자 및 사용자 부서의 개인/회사 일정 메모 조회", description = "Calendar에서 user 본인의 사용자 부서 공유 개인/회사 일정 메모를 조회할 때 사용")
		@Parameter(name = "schedule_no", description = "일정을 식별하기위한 일정 번호")
		@GetMapping("/user-dep-schedulememo")
		public List<Schedule_MemoVo> getScheduleMemoList(@RequestParam Long schedule_no) {
			return service.getScheduleMemoList(schedule_no);
		}
		
		//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (휴가를 클릭했을때)
		@Operation(summary = "user 사용자 및 사용자 부서의 휴가 일정 메모 조회", description = "Calendar에서 user 본인의 사용자 부서 공유 휴가 일정 메모를 조회할 때 사용")
		@Parameter(name = "leave_no", description = "일정을 식별하기위한 일정 번호")
		@GetMapping("/user-dep-leavememo")
		public List<Schedule_MemoVo> getLeaveMemoList(@RequestParam Long leave_no) {
			return service.getLeaveMemoList(leave_no);
		}
		
		//Calendar에서 사용자 및 사용자 부서 메모를 클릭 했을때 작성자의 정보를 추가 조회
		@Operation(summary = "user 사용자 및 사용자 부서 메모 작성자 조회", description = "Calendar에서 사용자 및 사용자 부서 메모를 클릭 했을때 작성자의 정보를 추가 조회할 때 사용")
		@Parameter(name = "memo_no", description = "메모 작성자를 식별하기위한 메모 번호")
		@GetMapping("/user-dep-memodetail")
		public UserVo getMemoDetail(@RequestParam Long memo_no) {
			return service.getMemoDetail(memo_no);
		}
		
		//Calendar에서 사용자 및 사용자 부서 메모를 추가할 때 사용
		@Operation(summary = "user 사용자 부서 일정 메모 추가", description = "Calendar에서 user 본인의 사용자 부서 일정 메모를 추가할 때 사용")
		@PostMapping("/user-dep-memo")
		public void createMemo(@RequestBody Schedule_MemoVo memoVo) {
			service.createMemo(memoVo);
		}
		
		//Calendar에서 사용자 및 사용자 부서 메모를 수정할 때 사용
		@Operation(summary = "user 사용자 부서 일정 메모 수정", description = "Calendar에서 user 본인의 사용자 부서 일정 메모를 수정할 때 사용")
		@PatchMapping("/user-dep-memo")
		public void updateMemo(@RequestBody Schedule_MemoVo memoVo) {
			service.updateMemo(memoVo);
		}
		
		//Calendar에서 사용자 및 사용자 부서 메모를 제거할 때 사용
		@Operation(summary = "user 사용자 부서 일정 메모 삭제", description = "Calendar에서 user 본인의 사용자 부서 일정 메모를 삭제할 때 사용")
		@Parameter(name = "memo_no", description = "메모 작성자를 식별하기위한 메모 번호")
		@DeleteMapping("/user-dep-memo")
		public void deleteMemo(@RequestParam Long memo_no) {
			service.deleteMemo(memo_no);
		}

}
