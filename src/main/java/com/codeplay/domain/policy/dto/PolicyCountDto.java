package com.codeplay.domain.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyCountDto {
	 private Integer policy_no;
	 private String policy_name;
	 private Integer count;

}
