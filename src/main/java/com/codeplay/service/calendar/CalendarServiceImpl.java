package com.codeplay.service.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.ScheduleVo;
import com.codeplay.domain.Schedule_MemoVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.calendar.vo.UserLeaveVo;
import com.codeplay.domain.calendar.vo.UserScheduleLeaveMemoVo;
import com.codeplay.domain.calendar.vo.UserScheduleVo;
import com.codeplay.mapper.calendar.CalendarMapper;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarMapper calendarMapper;

	@Override
	public List<ScheduleVo> getScheduleList(Long user_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getScheduleList(user_no);
	}

	@Override
	public List<Leave_ApprovalVo> getLeaveList(Long user_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getLeaveList(user_no);
	}

	@Override
	public void createSchedule(ScheduleVo schedule) {
		// TODO Auto-generated method stub
		calendarMapper.createSchedule(schedule);
	}

	@Override
	public void updateSchedule(ScheduleVo schedule) {
		// TODO Auto-generated method stub
		calendarMapper.updateSchedule(schedule);
	}

	@Override
	public void deleteSchedule(Long schedule_no) {
		// TODO Auto-generated method stub
		calendarMapper.deleteSchedule(schedule_no);
	}

	@Override
	public List<UserScheduleVo> getShereScheduleList(Long user_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getShereScheduleList(user_no);
	}

	@Override
	public List<UserLeaveVo> getShereLeaveList(Long user_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getShereLeaveList(user_no);
	}

	@Override
	public List<Schedule_MemoVo> getScheduleMemoList(Long schedule_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getScheduleMemoList(schedule_no);
	}

	@Override
	public List<Schedule_MemoVo> getLeaveMemoList(Long leave_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getLeaveMemoList(leave_no);
	}

	@Override
	public UserVo getMemoDetail(Long memo_no) {
		// TODO Auto-generated method stub
		return calendarMapper.getMemoDetail(memo_no);
	}

	@Override
	public void createMemo(Schedule_MemoVo memoVo) {
		// TODO Auto-generated method stub
		calendarMapper.createMemo(memoVo);
	}

	@Override
	public void updateMemo(Schedule_MemoVo memoVo) {
		// TODO Auto-generated method stub
		calendarMapper.updateMemo(memoVo);
	}

	@Override
	public void deleteMemo(Long memo_no) {
		// TODO Auto-generated method stub
		calendarMapper.deleteMemo(memo_no);
	}
	

}
