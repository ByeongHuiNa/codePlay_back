package com.codeplay.domain.access.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomAccessPageDto {
	
	private Integer page_no;
	private Integer role_level;
	private String page_url;
	private String page_tittle;
	private Date role_designated_date;
	private Integer page_default_role_level;

}
