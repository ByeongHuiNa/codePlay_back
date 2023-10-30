package com.codeplay.mapper.userAttend;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.UserVo;

@Mapper
public interface UserAttendMapper {
	// 사용자의 전체 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> findAttendByUserNo(int user_no);
	
	// 사용자의 월별 출퇴근(근태) 내역 : user_no, month 사용
	public List<AttendanceVo> findAttendByUserNoMonth(int user_no, int month);
	
	// 사용자가 속한 부서의 근태담당자 내역 : dept_no 사용
	public List<UserVo> findManagerByDeptNo(int dept_no);
	

	// 사용자의 오늘 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> findTodayByUserNo(int user_no);
	
	//사용자가 출근 기록
	public int startInsert(AttendanceVo atvo);
	
	public int endInsert(AttendanceVo atvo);
	
}