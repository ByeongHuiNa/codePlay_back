package com.codeplay.domain.attend.dto;

import java.sql.Time;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendEditDto {
	private Integer user_no;
	private Integer attend_no;
	private LocalTime attendoriginal_start_time;
	private LocalTime attendoriginal_end_time;
	private String attendoriginal_status;
	private String attendedit_title;
	private String attendedit_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Integer attendapp_user_no;
	private Integer attendapp_status;
}
