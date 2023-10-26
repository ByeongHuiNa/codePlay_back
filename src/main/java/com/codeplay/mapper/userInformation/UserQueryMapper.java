package com.codeplay.mapper.userInformation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.userInformation.dto.UserQueryDto;

@Mapper
public interface UserQueryMapper {
	public List<UserQueryDto> findAll();
	public List<UserQueryDto> findByUsername(CriteriaVo cri);
}
