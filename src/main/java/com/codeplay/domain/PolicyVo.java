package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//정책 테이블
public class PolicyVo {
	
	private Integer policy_no;
	
	private String policy_name;
	
	private Date standard_start_time;
	
	private Date standard_end_time;
	
	private String standard_weekend;

}
