package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//권한테이블
public class RoleVo {
	
	private Integer role_level;
	
	private String role_name;
	
	private String has_role;

}
