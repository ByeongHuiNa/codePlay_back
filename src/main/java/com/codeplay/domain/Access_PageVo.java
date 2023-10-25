package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//접근체이지 테이블
public class Access_PageVo {
	
	private Integer page_no;
	
	private String page_url;
	
	private String page_title;
	
	private String page_description;
	
	private Integer page_default_role_level;

}
