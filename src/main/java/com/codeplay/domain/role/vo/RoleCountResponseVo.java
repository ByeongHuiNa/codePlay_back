package com.codeplay.domain.role.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCountResponseVo {
	 private Integer user_no;
	 private String user_profile;
	 private Integer role_level;
	 private String dept_name;
	 private String role_name;
	 private String user_name;
	 private String user_position;
	 private String role_designated_date;

}
