package com.codeplay.domain.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLeaveLineRequestDto {
	// 결재자
	// leaveappln_order : 1차, 2차
	// leaveappln_status : 2 (결재대기)
	private Integer leaveapp_no;
	private Integer user_no;
	private Integer order;
}
