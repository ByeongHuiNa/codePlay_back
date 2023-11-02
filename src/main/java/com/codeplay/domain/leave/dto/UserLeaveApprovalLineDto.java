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
	private String user_name;
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start; 
	private Date leaveapp_end; 
	private Integer leaveapp_status;
	private String leaveapp_final_date; 
	private Integer leaveapp_type; 
	private String leaveapp_total;
	private Date leaveapp_req_date;
	private Integer firstapp_no;
	private Integer firstapp_user_no;
	private String firstapp_user_name;
	private Integer firstapp_status;
	private Integer secondapp_no;
	private Integer secondapp_user_no;
	private String secondapp_user_name;
	private Integer secondapp_status;

}
