package com.codeplay.domain.managerApproval.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //결재 : 승인,반려
public class ApprovalRequestVo {
	private Integer user_no;
	private Integer leaveapp_no;
	private Double leaveapp_total;
	private Integer leaveapp_type;
	private Integer leaveappln_no;
	private Integer leaveappln_order;
	private Integer leaveappln_status;
	private String leaveappln_reason;
	private Integer leaveapp_cancel_no;
}
