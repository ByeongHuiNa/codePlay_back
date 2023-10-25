package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//근태테이블
public class AttendanceVo {
	
	private Integer attend_no;
	
	private Integer user_no;
	
	private Date attend_date;
	
	private Date attend_start;
	
	private Date attend_end;
	
	private Date attend_total;
	
	private String attend_status;


}
