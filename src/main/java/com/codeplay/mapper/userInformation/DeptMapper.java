package com.codeplay.mapper.userInformation;

import com.codeplay.domain.userInformation.vo.DeptListResponseVo;
import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.DeptVo;

import java.util.List;


@Mapper
public interface DeptMapper {
	public DeptVo findByDeptNo(int dept_no);

    List<DeptListResponseVo> findAll();
}
