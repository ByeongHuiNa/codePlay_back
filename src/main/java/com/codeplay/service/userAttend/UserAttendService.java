package com.codeplay.service.userAttend;

import java.util.Date;
import java.util.List;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;
import com.codeplay.domain.attend.vo.UsersAttendVo;

public interface UserAttendService {
	// 사용자의 전체 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> getAttendByUserNo(int user_no);
	// 사용자의 월별 출퇴근(근태) 내역 : user_no, month 사용
	public List<AttendanceVo> getAttendByUserNoMonth(int user_no, int month);
	// 사용자의 특정한 날짜의 출퇴근(근태) 내역 : user_no, date 사용
	public AttendanceVo getAttendByUserNoDate(int user_no, int year, int month, int day);
	// 사용자가 속한 부서의 근태담당자 내역 : dept_no 사용
	public List<UserVo> getManagerByDeptNo(int dept_no);
	// 사용자의 전체 출퇴근(근태) 수정 내역 가져오기 : user_no 사용
	public List<UserAttendEditResponseVo> getAttendEditByUserNo(int user_no);
	// 사용자의 출퇴근 수정하기 : attendance_edit_approval 테이블에 저장
	public int addAttendEdit(UserAttendEditDto dto);
	// 사용자의 오늘 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> getTodayByUserNo(int user_no);
	//사용자가 출근기록
	public int saveStartAttendance(AttendanceVo atvo);
	//사용자가 퇴근기록
	public int saveEndAttendance(AttendanceVo atvo);
	//사용자의 주간 근무시간
	public List<AttendanceVo> getUserTotalAttend(int user_no);
	//부서별 사원들의 근태현황(일별)
	public List<UsersAttendVo> getUsersAttend(int dept_no);
}
