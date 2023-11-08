package com.codeplay.domain.managerApproval.dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendPolicyDto {
	private Time standard_start_time;
	private Time standard_end_time;
}
