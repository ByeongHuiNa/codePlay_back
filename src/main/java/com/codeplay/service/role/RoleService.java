package com.codeplay.service.role;

import java.util.List;

import com.codeplay.domain.role.dto.*;


public interface RoleService {
	
	public List<RoleQueryListDto> getUserByRoleQuery(RoleQueryDto roleQuery);
	public RoleUserDetailDto getUserDetailByUserNo(int user_no);
	public RoleDeleteDto save(Integer userNo, RoleUserDetailDto request);
	public List<RoleCountDto> getRoleCount();
	

}
