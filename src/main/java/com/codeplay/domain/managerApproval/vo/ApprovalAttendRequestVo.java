package com.codeplay.domain.managerApproval.vo;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttendRequestVo {
	// 수정 신청자 번호는 URL로 받아오기
	private Integer attend_no;
	private Integer attendapp_no;
	private Integer attendapp_status;
	private String attendapp_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
}
