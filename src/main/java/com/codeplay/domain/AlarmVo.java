package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//알림테이블
public class AlarmVo {
	
	private Integer alarm_no;
	
	private Integer user_no;
	
	private String alarm_content;
	
	private Integer alarm_kind;
	
	private Integer alarm_status;
	
	private Date alarm_date;
	
	private Integer alarm_send_user_no;

}
