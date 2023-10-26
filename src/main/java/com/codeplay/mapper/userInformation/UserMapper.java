package com.codeplay.mapper.userInformation;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.UserVo;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;

@Mapper
public interface UserMapper {
	public UserVo findByUserNo(int user_no);
	public void save(UserInformationPatchDto userPatch);
}
