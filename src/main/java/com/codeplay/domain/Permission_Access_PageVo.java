package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission_Access_PageVo {
	
	private Integer role_level;
	
	private Integer page_no;
	
	private Date permission_access_page;

}
