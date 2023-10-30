package com.codeplay.domain.policy.dto;

import com.codeplay.domain.CriteriaVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyQueryDto {
	private CriteriaVo Criteria;
	private int policy_no;

}
