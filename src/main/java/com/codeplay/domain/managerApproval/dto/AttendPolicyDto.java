package com.codeplay.domain.managerApproval.dto;

import java.sql.Time;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendPolicyDto {
	private LocalTime standard_start_time;
	private LocalTime standard_end_time;
}
