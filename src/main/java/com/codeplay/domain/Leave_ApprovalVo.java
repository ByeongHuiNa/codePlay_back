package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//휴가결재 테이블
public class Leave_ApprovalVo {
	
	private Integer leaveapp_no;
	
	private Integer user_no;
	
	private Integer leaveapp_status;
	
	private Date leaveapp_final_date;
	
	private String leaveapp_title;
	
	private Integer leaveapp_type;
	
	private Date leaveapp_start;
	
	private Date leaveapp_end;
	
	private Integer leaveapp_total;
	
	private Integer leaveapp_cancel_no;
	
	private String leaveapp_content;

}
