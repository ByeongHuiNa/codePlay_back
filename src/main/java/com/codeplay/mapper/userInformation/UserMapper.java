package com.codeplay.mapper.userInformation;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.UserVo;

@Mapper
public interface UserMapper {
	public UserVo findByUserNo(int user_no);
}
