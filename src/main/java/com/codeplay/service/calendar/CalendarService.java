package com.codeplay.service.calendar;

import java.util.List;

import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.ScheduleVo;
import com.codeplay.domain.Schedule_MemoVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.calendar.vo.UserLeaveVo;
import com.codeplay.domain.calendar.vo.UserScheduleLeaveMemoVo;
import com.codeplay.domain.calendar.vo.UserScheduleVo;

public interface CalendarService {
	
	//사용자 일정
		//Calendar에서 user 본인의 사용자 일정을 조회할 때 사용
		public List<ScheduleVo> getScheduleList(Long user_no);
		
		//Calendar에서 user 본인의 사용자 휴가를 조회할 때 사용
		public List<Leave_ApprovalVo> getLeaveList(Long user_no);
		
		//Calendar에서 user 본인의 사용자 일정을 추가할 때 사용
		public void createSchedule(ScheduleVo schedule);
		
		//Calendar에서 user 본인의 사용자 일정을 수정할 때 사용
		public void updateSchedule(ScheduleVo schedule);
		
		//Calendar에서 user 본인의 사용자 일정을 삭제할 때 사용
		public void deleteSchedule(Long schedule_no);
		
		
	//사용자 및 사용자 부서의 공유 일정 (사용자, 일정 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서의 공유 일정을 조회할 때 사용
		public List<UserScheduleVo> getShereScheduleList(Long user_no);
		
		
	//사용자 및 사용자 부서의 공유 휴가 (사용자, 휴가 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서의 휴가 일정을 조회할 때 사용
		public List<UserLeaveVo> getShereLeaveList(Long user_no);
		
		
	//사용자 및 사용자 부서의 메모 (사용자, 일정, 휴가, 일정 메모 테이블 조인)
		//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (일정을 클릭했을때)
		public List<Schedule_MemoVo> getScheduleMemoList(Long schedule_no);
		
		//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (휴가를 클릭했을때)
		public List<Schedule_MemoVo> getLeaveMemoList(Long leave_no);
		
		//Calendar에서 사용자 및 사용자 부서 메모를 클릭 했을때 작성자의 정보를 추가 조회
		public UserVo getMemoDetail(Long memo_no);
		
		//Calendar에서 사용자 및 사용자 부서 메모를 추가할 때 사용
		public void createMemo(Schedule_MemoVo memoVo);
		
		//Calendar에서 사용자 및 사용자 부서 메모를 수정할 때 사용
		public void updateMemo(Schedule_MemoVo memoVo);
		
		//Calendar에서 사용자 및 사용자 부서 메모를 제거할 때 사용
		public void deleteMemo(Long memo_no);
		
}
