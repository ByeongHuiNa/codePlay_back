package com.codeplay.mapper.userInformation;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.DeptVo;


@Mapper
public interface DeptMapper {
	public DeptVo findByDeptNo(int dept_no);
}
