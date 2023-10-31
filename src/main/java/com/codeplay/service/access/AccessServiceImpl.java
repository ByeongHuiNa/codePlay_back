package com.codeplay.service.access;

import com.codeplay.domain.User_RoleVo;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;
import com.codeplay.mapper.access.AccessMapper;
import com.codeplay.mapper.role.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccessServiceImpl implements AccessService {
	@Autowired
	AccessMapper accessMapper;

	@Override
	public List<RoleAccessPageDto> getRoleAccessByRoleLevel(int roleLevel) {
		return accessMapper.findByRoleLevel(roleLevel);
	}
}
