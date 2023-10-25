package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//부서테이블
public class DeptVo {
	
	private Integer dept_no;
	
	private String dept_name;

}
