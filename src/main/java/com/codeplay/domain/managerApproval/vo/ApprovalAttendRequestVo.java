package com.codeplay.domain.managerApproval.vo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttendRequestVo {
	// 수정 신청자 번호는 URL로 받아오기
	private Integer attend_no;
	private Integer user_no;
	private LocalTime attend_start;
	private LocalTime attend_end;
	private LocalDate attend_date; // 날짜만 (시간은 빼고)
	private String attend_status;
	private Integer attendapp_no;
	private Integer attendapp_status;
	private String attendapp_reason;
	private Integer attendedit_kind;
	private LocalTime attendedit_start_time;
	private LocalTime attendedit_end_time;
}
