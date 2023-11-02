package com.codeplay.domain.managerModify.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponseVo {
	private Integer user_no;
	private String user_name;
	private String user_position;
	private String user_email;
	private String user_phone;
	private String user_profile;
	private Date user_hired_date;
	private Integer dept_no;
	private String dept_name;
}
