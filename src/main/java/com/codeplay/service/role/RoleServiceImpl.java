package com.codeplay.service.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.User_RoleVo;
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
import com.codeplay.mapper.policy.PolicyMapper;
import com.codeplay.mapper.role.RoleMapper;
import com.codeplay.mapper.userInformation.DeptAssignMapper;
import com.codeplay.mapper.userInformation.DeptMapper;
import com.codeplay.mapper.userInformation.UserMapper;
import com.codeplay.mapper.userInformation.UserQueryMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<RoleQueryListDto> getUserByRoleQuery(RoleQueryDto roleQuery) {
		return roleMapper.findByRoleQueryList(roleQuery);
	}

	@Override
	public RoleUserDetailDto getUserDetailByUserNo(int user_no) {
		RoleUserDetailDto user = new RoleUserDetailDto();
		user.setRole(roleMapper.findByUserNo(user_no));
		for(User_RoleVo role: user.getRole()) {
			if(role.getRole_level()==2) {
				user.setAttend_ma(roleMapper.findAttendMaByUserNo(user_no));				
			}
		}
		return user;
	}

	//기존에 있던 기록 지우고, 새로운 기록 insert
	//TODO: 최적화시, 기존에 있던 기록이 변했는지 확인후, 변하지 않았으면 안바꿈.
	@Override
	public void save(RoleUserDetailDto request) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoleCountDto> getRoleCount() {
		return roleMapper.findRoleCount();
	}

	
	

}
