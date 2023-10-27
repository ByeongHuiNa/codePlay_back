package com.codeplay.mapper.policy;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.Dept_AssignVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;

@Mapper
public interface PolicyMapper {
	public List<PolicyUserDto> findByQueryDto(PolicyQueryDto query);
	public PolicyUserDetailDto findByUserNo(int user_no);
	public List<PolicyCountDto> findPolicyCount();
}
