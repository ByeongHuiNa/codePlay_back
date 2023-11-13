package com.codeplay.domain.attend.vo;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersAttendWeekVo {
	
	private Integer dept_no;
	private String dept_name;
	private Integer user_no;
	private String dept_designated_date;
	private String user_name;
	private String user_position;
	private Integer attend_no;
	private Time attend_start;
	private Time attend_end;
	private String attend_status;
	private Time attend_total;
	private String attend_date;
	private String monday_status;
	private String tuesday_status;
	private String wednesday_status;
	private String thursday_status;
	private String friday_status;
	private String saturday_status;
	private String sunday_status;
	private String total_time;
	

}
