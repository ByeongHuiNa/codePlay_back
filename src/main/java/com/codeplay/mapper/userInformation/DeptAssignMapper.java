package com.codeplay.mapper.userInformation;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.Dept_AssignVo;

@Mapper
public interface DeptAssignMapper {
	public Dept_AssignVo findByUserNo(int user_no);
}
