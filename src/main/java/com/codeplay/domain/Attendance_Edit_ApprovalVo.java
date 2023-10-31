package com.codeplay.domain;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//근태수정결재 테이블
public class Attendance_Edit_ApprovalVo {
	
	private Integer attendapp_no;
	
	private Integer user_no;
	
	private Integer attend_no;
	
	private String attendedit_title;
	
	private String attendedit_reason;
	
	private Date attendedit_date;
	
	private String attendedit_kind;
	
	private Time attendedit_start_time;
	
	private Time attendedit_end_time;
	
	private Integer attendapp_user_no;
	
	private boolean attendapp_status;
	
	private String attendapp_reason;
	
	private Date attendapp_date;

}
