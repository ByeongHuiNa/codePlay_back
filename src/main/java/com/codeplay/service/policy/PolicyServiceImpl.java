package com.codeplay.service.policy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
import com.codeplay.mapper.policy.PolicyMapper;
import com.codeplay.mapper.userInformation.DeptAssignMapper;
import com.codeplay.mapper.userInformation.DeptMapper;
import com.codeplay.mapper.userInformation.UserMapper;
import com.codeplay.mapper.userInformation.UserQueryMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyMapper policyMapper;

	@Override
	public List<PolicyUserDto> getUserByPolicyNo(PolicyQueryDto query) {
		return policyMapper.findByQueryDto(query);
	}


	@Override
	public PolicyUserDetailDto getPolicyUserByUserNo(int user_no) {
		return policyMapper.findByUserNo(user_no);
	}


	@Override
	public List<PolicyCountDto> getPolicyCount() {
		return policyMapper.findPolicyCount();
	}
	

}
