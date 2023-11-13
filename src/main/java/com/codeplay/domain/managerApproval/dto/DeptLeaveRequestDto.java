package com.codeplay.domain.managerApproval.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLeaveRequestDto {
	private Integer user_no;
	private LocalDate leaveapp_date; 
}
