package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//부서지정 테이블
public class Dept_AssignVo {
	
	private Integer user_no;
	
	private Integer dept_no;
	
	private Date dept_designated_date;
	
	

}
