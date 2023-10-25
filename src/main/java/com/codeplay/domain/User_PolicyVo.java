package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//사용자별정책 테이블
public class User_PolicyVo {
	
	private Integer policy_no;
	
	private Integer user_no;
	
	private Date policy_designated_date;

}
