package com.codeplay.domain.userInformation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryResponseVo {
	private Integer user_no;
	private String user_profile;
	private String dept_name;
	private String user_name;
	private String user_position;
	private String user_birth_date;

}
