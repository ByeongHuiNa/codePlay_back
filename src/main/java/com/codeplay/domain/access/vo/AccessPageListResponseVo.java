package com.codeplay.domain.access.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessPageListResponseVo {
	 private Integer page_no;
	 private String page_title;
	 private Integer page_default_role_no;
}

