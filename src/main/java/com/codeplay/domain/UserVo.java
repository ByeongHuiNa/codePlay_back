package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//사용자테이블
public class UserVo {
	
	private Integer user_no;
	
	private String user_name;
	
	private String user_position;
	
	private String user_email;
	
	private String user_password;
	
	private boolean user_password_is_temp;
	
	private String user_phone;
	
	private String user_address;
	
	private String user_profile;
	
	private Date user_hired_date;
	
	private Date user_birth_date;

}
