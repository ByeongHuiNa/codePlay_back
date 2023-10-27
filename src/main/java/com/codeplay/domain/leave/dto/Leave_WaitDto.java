package com.codeplay.domain.leave.dto;

import java.util.Date;

import com.codeplay.domain.LeaveVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave_WaitDto {
		
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
