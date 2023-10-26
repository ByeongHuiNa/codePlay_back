package com.codeplay.domain.userInformation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDto {
	private Integer user_no;
	private String user_profile;
	private String dept_name;
	private String user_name;
	private String user_position;
	private Date user_birth_date;

}
