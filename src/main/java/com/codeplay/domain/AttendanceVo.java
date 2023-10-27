package com.codeplay.domain;

import java.sql.Time;
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
	
	private Time attend_start;
	
	private Time attend_end;
	
	private Time attend_total;
	
	private String attend_status;

}
