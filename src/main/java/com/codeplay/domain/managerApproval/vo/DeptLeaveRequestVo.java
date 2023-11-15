package com.codeplay.domain.managerApproval.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLeaveRequestVo {
	private Integer user_no;
	private String leaveapp_start; 
	private String leaveapp_end; 
	private Integer leaveapp_status;
	private Integer leaveapp_type; 
	private Double leaveapp_total;
}
