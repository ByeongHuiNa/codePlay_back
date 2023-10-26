package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//휴가테이블
public class LeaveVo {
	
	private Integer user_no;
	
	private Integer leave_no;
	
	private Integer leave_year;
	
	private Integer leave_total;
	
	private Integer leave_use;
	
	private Integer leave_remain;

}
