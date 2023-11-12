package com.codeplay.service.userAttend;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;
import com.codeplay.domain.attend.vo.UsersAttendVo;
import com.codeplay.domain.attend.vo.UsersAttendWeekVo;
import com.codeplay.mapper.userAttend.UserAttendEditMapper;
import com.codeplay.mapper.userAttend.UserAttendMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAttendServiceImpl implements UserAttendService {
	@Autowired
	UserAttendMapper userAttendMapper;
	
	@Autowired
	UserAttendEditMapper userAttendEditMapper;
	
	@Override // 출퇴근 내역
	public List<AttendanceVo> getAttendByUserNo(int user_no) {
		return userAttendMapper.findAttendByUserNo(user_no);
	}

	@Override // 같은 부서 근태담당자 내역
	public List<UserVo> getManagerByDeptNo(int dept_no) {
		return userAttendMapper.findManagerByDeptNo(dept_no);
	}

	@Override // 출퇴근 수정 내역
	public List<UserAttendEditResponseVo> getAttendEditByUserNo(int user_no) {
		return userAttendEditMapper.findAttendEditByUserNo(user_no);
	}

	@Override
	public int addAttendEdit(UserAttendEditDto dto) {
		return userAttendEditMapper.saveAttendEdit(dto);
	}

	@Override//출퇴근 내역 월별
	public List<AttendanceVo> getAttendByUserNoMonth(int user_no, int month) {
		return userAttendMapper.findAttendByUserNoMonth(user_no, month);
	}

	@Override//출퇴근 내역 오늘
	public List<AttendanceVo> getTodayByUserNo(int user_no) {
		return userAttendMapper.findTodayByUserNo(user_no);
	}

	@Override//출근 기록
	public int saveStartAttendance(int user_no, AttendanceVo atvo) {
		return userAttendMapper.startInsert(user_no, atvo);
	}

	@Override//퇴근 기록
	public int saveEndAttendance(int user_no, AttendanceVo atvo) {
		return userAttendMapper.endInsert(user_no, atvo);
	}

	@Override//사용자의 주간근무시간
	public List<AttendanceVo> getUserTotalAttend(int user_no) {
		return userAttendMapper.getUserAttendTotal(user_no);
	}

	@Override//부서별 사원들의 근태현황(일별)
	public List<UsersAttendVo> getUsersAttendDay(int dept_no) {
		return userAttendMapper.seeUsersAttendDay(dept_no);
	}
	@Override //출퇴근 내역 특정 날짜
	public AttendanceVo getAttendByUserNoDate(int user_no, int year, int month, int day) {
		return userAttendMapper.findAttendByUserNoDate(user_no, year, month, day);
	}

	@Override//부서별 사원들의 근태현황(주별)
	public List<UsersAttendWeekVo> getUsersAttendWeek(int dept_no, String week_monday) {
		return userAttendMapper.seeUsersAttendWeek(dept_no, week_monday);
	}

}
