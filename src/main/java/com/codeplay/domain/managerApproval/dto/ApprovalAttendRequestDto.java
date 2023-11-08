package com.codeplay.domain.managerApproval.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttendRequestDto {
	private Integer attend_no;
	private Integer user_no;
	private Time attend_start;
	private Time attend_end;
	private LocalDate attend_date; // 날짜만 (시간은 빼고)
	private Integer attendapp_no;
	private Integer attendapp_status;
	private String attendapp_reason;
	private Integer attendedit_kind;
	private Time attendedit_start_time;
	private Time attendedit_end_time;
	private Time standard_start_time;
	private Time standard_end_time;
}
