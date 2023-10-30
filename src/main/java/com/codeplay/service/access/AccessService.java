package com.codeplay.service.access;

import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;

import java.util.List;


public interface AccessService {
	List<RoleAccessPageDto> getRoleAccessByRoleLevel(int roleLevel);
}
