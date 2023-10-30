package com.codeplay.domain.policy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyCountResponseVo {
	 private Integer policy_no;
	 private String policy_name;
	 private Integer count;

}
