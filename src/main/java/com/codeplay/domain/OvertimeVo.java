package com.codeplay.domain;

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
	
	private String overtime_time;
	
	private String overtime_content;
	
	private String overtime_reason;
	
	private Date overtime_date;
	
	private Integer overtime_user_no;
	
	private boolean overtime_status;
	
	private String overtimeapp_reason;
	
	private Date overtimeapp_date;

}
