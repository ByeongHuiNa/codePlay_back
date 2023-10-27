package com.codeplay.domain.userAttend.dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendEditDto {
	private Integer user_no;
	private Integer attend_no;
	private String attendedit_title;
	private String attendedit_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Integer attendapp_user_no;
	private Integer attendapp_status;
}
