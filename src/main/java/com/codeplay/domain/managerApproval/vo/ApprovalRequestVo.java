package com.codeplay.domain.managerApproval.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //결재 : 승인,반려
public class ApprovalRequestVo {
	private Integer approval_kind; // attendance : 0, leave : 1
	private Integer approval_no; // attendapp_no  OR  leaveappln_no
	private Integer approval_order;
	private Integer approval_status;
	private Integer approval_reason;
}
