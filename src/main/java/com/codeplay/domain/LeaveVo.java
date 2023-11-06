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
	
	private Double leave_total;
	
	private Double leave_use;
	
	private Double leave_remain;

}
