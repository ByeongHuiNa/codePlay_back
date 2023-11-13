package com.codeplay.service.managerModify;

import java.util.List;

import com.codeplay.domain.managerModify.vo.UserAttendModifyVo;
import com.codeplay.domain.managerModify.vo.UserListResponseVo;

public interface ManagerModifyService {
	// 사용자와 같은 부서의 전체 직원 목록 가져오기
	public List<UserListResponseVo> getAllUsersByUserNo(int user_no);
	// 근태담당자가 같은 부서의 직원 출퇴근 수정 처리
	public void modifyAttend(UserAttendModifyVo vo);
}
