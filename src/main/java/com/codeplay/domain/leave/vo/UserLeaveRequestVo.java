package com.codeplay.domain.leave.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 휴가 신청
public class UserLeaveRequestVo {
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start;
	private Date leaveapp_end;
	private Double leaveapp_total;
	private Integer leaveapp_type; // 휴가종류
	private Integer firstapp_no;
	private Integer secondapp_no;
}
