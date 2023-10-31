package com.codeplay.domain.leave.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 휴가 취소 신청
public class UserLeaveCancelRequestVo { 
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start; // 휴가 시작일
	private Date leaveapp_end; // 휴가 종료일
	private Double leaveapp_total;
	private Integer leaveapp_cancel_no;
	private Integer firstapp_no;
}
