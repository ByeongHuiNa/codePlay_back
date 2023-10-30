package com.codeplay.mapper.userInformation;

import com.codeplay.security.UserDto;
import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.UserVo;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;

import java.util.Optional;

@Mapper
public interface UserMapper {
	public UserVo findByUserNo(int user_no);
	public void save(UserInformationPatchDto userPatch);

	public Optional<UserDto> login(UserDto userDto);
}
