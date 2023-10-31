package com.codeplay.domain.access.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserListDto {
	 private Integer user_no;
	 private String user_profile;
	 private String dept_name;
	 private String user_name;
	 private String user_position;
	 private Date role_designated_date;

}
