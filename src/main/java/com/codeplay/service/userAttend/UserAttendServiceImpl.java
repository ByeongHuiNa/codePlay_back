package com.codeplay.service.userAttend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.Attendance_Edit_ApprovalVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.userAttend.dto.UserAttendEditDto;
import com.codeplay.mapper.userAttend.UserAttendEditMapper;
import com.codeplay.mapper.userAttend.UserAttendMapper;
import com.codeplay.service.userLeave.UserLeaveServiceImpl;

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
	public List<Attendance_Edit_ApprovalVo> getAttendEditByUserNo(int user_no) {
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

	@Override//출퇴근 기록
	public int saveAttendance(AttendanceVo atvo) {
		return userAttendMapper.startInsert(atvo);
	}

}
