package com.codeplay.domain.userInformation.dto;

import java.util.Date;

import com.codeplay.domain.DeptVo;
import com.codeplay.domain.Dept_AssignVo;
import com.codeplay.domain.UserVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationPatchDto {
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
