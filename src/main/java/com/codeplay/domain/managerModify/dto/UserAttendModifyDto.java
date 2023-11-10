package com.codeplay.domain.managerModify.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.codeplay.domain.attend.dto.UserAttendEditDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendModifyDto {
	private Integer user_no;
	private Integer attend_no;
	private String attendedit_title;
	private Integer attendedit_kind;
	private Date attendedit_date;
	private LocalTime attendedit_start_time;
	private LocalTime attendedit_end_time;
	private Integer attendapp_user_no;
	private Integer attendapp_status;
	private String attendapp_reason;
	private LocalDate attendapp_date;
	private LocalTime attendoriginal_start_time;
	private LocalTime attendoriginal_end_time;
	private String attendoriginal_status;
}
