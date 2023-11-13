package com.codeplay.mapper.userAttend;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.vo.UsersAttendVo;
import com.codeplay.domain.attend.vo.UsersAttendWeekVo;

@Mapper
public interface UserAttendMapper {
	// 사용자의 전체 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> findAttendByUserNo(int user_no);
	
	// 사용자의 월별 출퇴근(근태) 내역 : user_no, month 사용
	public List<AttendanceVo> findAttendByUserNoMonth(int user_no, int month);
	
	// 사용자의 특정한 날짜의 출퇴근(근태) 내역 : user_no, date 사용
	public AttendanceVo findAttendByUserNoDate(int user_no, int year, int month, int day);
	
	// 사용자가 속한 부서의 근태담당자 내역 : dept_no 사용
	public List<UserVo> findManagerByDeptNo(int dept_no);

	// 사용자의 오늘 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> findTodayByUserNo(int user_no);
	
	//사용자가 출근 기록
	public int startInsert(int user_no, AttendanceVo atvo);
	
	//사용자가 퇴근 기록
	public int endInsert(int user_no, AttendanceVo atvo);
	
	//사용자의 주간 근무시간
	public List<AttendanceVo> getUserAttendTotal(int user_no);
	
	//부서별 사원들의 근태현황(일별)
	public List<UsersAttendVo> seeUsersAttendDay(int dept_no); 
	
	//부서별 사원들의 근태현황(주별)
	public List<UsersAttendWeekVo> seeUsersAttendWeek(int dept_no, String week_monday);
	
	//결근데이터 자동삽입
	public int autoInsert(AttendanceVo atvo);
	
}