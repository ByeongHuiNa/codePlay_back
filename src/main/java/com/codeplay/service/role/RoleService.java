package com.codeplay.service.role;

import java.util.List;

import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;


public interface RoleService {
	
	public List<RoleQueryListDto> getUserByRoleQuery(RoleQueryDto roleQuery);
	public RoleUserDetailDto getUserDetailByUserNo(int user_no);
	public void save(Integer userNo, RoleUserDetailDto request);
	public List<RoleCountDto> getRoleCount();
	

}
