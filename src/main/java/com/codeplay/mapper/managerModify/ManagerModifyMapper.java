package com.codeplay.mapper.managerModify;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.managerModify.vo.UserListResponseVo;

@Mapper
public interface ManagerModifyMapper {
	// 로그인 한 사용자의 부서 번호 : user_no 사용
	public int findDeptNoByUserNo(int user_no);
	// 로그인 한 사용자와 같은 부서의 전체 직원 목록 : dept_no 사용
	public List<UserListResponseVo> findAllUsersByDeptNo(int dept_no);
}
