package com.codeplay.domain.role.dto;

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
public class RoleQueryListDto {
	private Integer user_no;
	private String user_profile;
	private Integer role_level;
	private String dept_name;
	private String role_name;
	private String user_name;
	private String user_position;
	private Date role_designated_date;

}
