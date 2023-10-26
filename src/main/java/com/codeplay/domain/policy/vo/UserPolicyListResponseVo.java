package com.codeplay.domain.policy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPolicyListResponseVo {
	 private Integer user_no;
	 private String user_profile;
	 private Integer policy_no;
	 private String dept_name;
	 private String user_name;
	 private String user_position;
	 private String policy_designated_date;

}
