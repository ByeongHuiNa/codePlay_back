package com.codeplay.domain;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//초과근무 테이블
public class OvertimeVo {
	
	private Integer overtime_no;
	
	private Integer attend_no;
	
	private Integer overtime_type;
	
	private Time overtime_time;
	
	private String overtime_content;
	
	private String overtime_reason;
	
	private Date overtime_date;
	
	private Integer overtimeapp_user_no;
	
	private Integer overtimeapp_status;
	
	private String overtimeapp_reason;
	
	private Date overtimeapp_date;

}
