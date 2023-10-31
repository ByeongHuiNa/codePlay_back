package com.codeplay.domain.leave.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLeaveResponseVo {
	private Integer leaveapp_no; 
	private Integer user_no;
	private String user_name;
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start; // 휴가 시작일
	private Date leaveapp_end; // 휴가 종료일
	private Integer leaveapp_status; // 최종결재상태
	private Date leaveapp_final_date; // 최종결재일
	private Integer leaveapp_type; // 휴가종류
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
