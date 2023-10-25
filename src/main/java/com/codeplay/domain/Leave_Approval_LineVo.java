package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//휴가결재선 테이블
public class Leave_Approval_LineVo {
	
	private Integer leaveappln_no;
	
	private Integer leaveapp_no;
	
	private Integer leaveappln_user_no;
	
	private String leaveappln_order;
	
	private boolean leaveappln_status;
	
	private Date leaveappln_date;
	
	private String leaveappln_reason;
	
	
}
