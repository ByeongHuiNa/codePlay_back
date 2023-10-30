package com.codeplay.service.policy;

import java.util.List;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;


public interface PolicyService {
	
	//Join해서 데이터 가져옴.
	public List<PolicyUserDto> getUserByPolicyNo(PolicyQueryDto query);
	public PolicyUserDetailDto getPolicyUserByUserNo(int user_no);
	public List<PolicyCountDto> getPolicyCount();
	
	

}
