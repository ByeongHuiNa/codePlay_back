package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//사용자권한 테이블
public class User_RoleVo {
	
	private Integer role_designated_no;
	
	private Integer user_no;
	
	private Integer role_level;
	
	private Integer page_no;
	
	private Date role_designated_date;

}
