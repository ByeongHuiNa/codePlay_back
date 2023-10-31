package com.codeplay.domain.leave.dto;

import java.util.Date;

import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.UserVo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaveApprovalLineDto {
	
	private Integer leaveapp_no;
	
	private Integer user_no;
	
	private String leaveapp_title;
	
	private String leaveapp_content;
	
	private Date leaveapp_start;
	
	private Date leaveapp_end;
	
	private Date leaveapp_final_date;
	
	private Integer leaveapp_type;
	
	private String leaveapp_total;
	
	private String one;
	
	private String two;
	
	//private Integer leaveappln_order;
	
	private Integer leaveapp_status;
	
	//private Date leaveappln_date;
	
	//private String leaveappln_reason;

}
