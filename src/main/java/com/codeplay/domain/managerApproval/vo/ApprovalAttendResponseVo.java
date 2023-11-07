package com.codeplay.domain.managerApproval.vo;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 출퇴근 수정 신청 내역 조회
public class ApprovalAttendResponseVo {
	// attendance
	private Integer attend_no; 
	private Integer user_no;
	private String user_name;
	private Time attend_start;
	private Time attend_end;
	private String attend_status;
	private Date attend_date;
	private Time attend_total;
	// attendance_edit_approval
	private Integer attendapp_no; 
	private Integer attendapp_user_no;
	private String attendapp_user_name;
	private String attendedit_title;
	private String attendedit_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Date attendedit_date;
	private Integer attendapp_status;
	private String attendapp_reason;
	private Date attendapp_date;
}