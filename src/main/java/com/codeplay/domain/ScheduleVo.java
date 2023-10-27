package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVo {
	
	private Integer schedule_no;
	
	private Integer user_no;
	
	private Date schedule_startday;
	
	private Date schedule_endday;
	
	private String schedule_type;
	
	private String schedule_title;
	
	private boolean schedule_allday;
	
	private String schedule_description;
	
	private boolean schedule_share;
	
	private boolean schedule_cardview;

}
