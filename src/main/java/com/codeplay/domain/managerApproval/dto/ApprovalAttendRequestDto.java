package com.codeplay.domain.managerApproval.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttendRequestDto {
	private Integer attend_no;
	private Integer user_no;
	private LocalTime attend_start;
	private LocalTime attend_end;
	private LocalTime attend_total;
	private String attend_status;
	private LocalDate attend_date; // 날짜만 (시간은 빼고)
	private Integer attendapp_no;
	private Integer attendapp_status;
	private Integer attendedit_kind;
	private LocalTime standard_start_time;
	private LocalTime standard_end_time;
}
