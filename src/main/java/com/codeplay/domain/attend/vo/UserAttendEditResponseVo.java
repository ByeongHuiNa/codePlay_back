package com.codeplay.domain.attend.vo;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAttendEditResponseVo {
	private Integer attendapp_no;
	private Integer user_no;
	private Integer attend_no;
	private Date attend_date;
	private String attendedit_title;
	private String attendedit_reason;
	private Date attendedit_date;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Integer attendapp_user_no;
	private String attendapp_user_name;
	private Integer attendapp_status;
	private String attendapp_reason;
	private Date attendapp_date;
}
