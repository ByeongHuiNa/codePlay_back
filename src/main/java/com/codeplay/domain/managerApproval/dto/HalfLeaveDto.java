package com.codeplay.domain.managerApproval.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HalfLeaveDto {
	private Integer user_no;
	private LocalDate attend_date;
}
