package com.codeplay.domain.policy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPolicyDetailRequestVo {
	 private Integer user_no;
	 private Integer policy_no;
	 private String standard_start_time;
	 private String standard_end_time;
	 private String policy_designated_date;
}
