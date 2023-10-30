package com.codeplay.service.userAttend;

import java.util.List;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;

public interface UserAttendService {
	// 사용자의 전체 출퇴근(근태) 내역 : user_no 사용
	public List<AttendanceVo> getAttendByUserNo(int user_no);
	// 사용자가 속한 부서의 근태담당자 내역 : dept_no 사용
	public List<UserVo> getManagerByDeptNo(int dept_no);
	// 사용자의 전체 출퇴근(근태) 수정 내역 가져오기 : user_no 사용
	public List<UserAttendEditResponseVo> getAttendEditByUserNo(int user_no);
	// 사용자의 출퇴근 수정하기 : attendance_edit_approval 테이블에 저장
	public int addAttendEdit(UserAttendEditDto dto);
}
