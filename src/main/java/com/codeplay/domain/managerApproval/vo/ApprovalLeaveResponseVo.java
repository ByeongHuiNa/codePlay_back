package com.codeplay.domain.managerApproval.vo;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 휴가 신청 내역 조회
public class ApprovalLeaveResponseVo {
	// leave_approval
	private Integer leaveapp_no; 
	private Integer user_no;
	private String user_name;
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start; 
	private Date leaveapp_end; 
	private Integer leaveapp_status;
	private Date leaveapp_final_date; 
	private Integer leaveapp_type; 
	private Integer leaveapp_total;
	private Date leaveapp_req_date;
	// leave_approval_line
	private Integer leaveappln_no;
	private Integer leaveappln_user_no;
	private String leaveappln_user_name;
	private Integer leaveappln_order;
	private Integer leaveappln_status;
	private String leaveappln_date;
	private String leaveappln_reason;
}