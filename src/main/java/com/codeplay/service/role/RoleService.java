package com.codeplay.service.role;

import java.util.List;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;


public interface RoleService {
	
	public List<RoleQueryListDto> getUserByRoleQuery(RoleQueryDto roleQuery);
	public RoleUserDetailDto getUserDetailByUserNo(int user_no);
	public void save(RoleUserDetailDto request);
	public List<RoleCountDto> getRoleCount();
	

}
