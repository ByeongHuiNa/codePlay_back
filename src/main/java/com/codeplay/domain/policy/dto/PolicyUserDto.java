package com.codeplay.domain.policy.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyUserDto {
	 private Integer user_no;
	 private String user_profile;
	 private Integer policy_no;
	 private String dept_name;
	 private String user_name;
	 private String user_position;
	 private Date policy_designated_date;

}
