package com.codeplay.service.access;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;

import java.util.List;


public interface AccessService {
	List<RoleAccessPageDto> getRoleAccessByRoleLevel(int roleLevel);

	RoleAccessCountResponseVo getCustomAccessCount();

	List<CustomUserListDto> getCustomUserList(CriteriaVo cri);

	List<CustomAccessPageDto> getAccessPageList(int user_no);
}
