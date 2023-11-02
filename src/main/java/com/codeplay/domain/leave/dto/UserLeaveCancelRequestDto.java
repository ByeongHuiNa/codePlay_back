package com.codeplay.domain.leave.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 휴가 취소 신청
public class UserLeaveCancelRequestDto {
	// leave_approval
	// leaveapp_status : 3 (결재대기) 최종결재상태
	// leaveapp_req_data : now() 현재 날짜
	private Integer user_no;
	private String leaveapp_title; 
	private String leaveapp_content;
	private Date leaveapp_start; 
	private Date leaveapp_end; 
	private Double leaveapp_total;
	private Integer leaveapp_type; 
	private Integer leaveapp_cancel_no; // 휴가 취소 신청일 때 필요
	// selectKey로 자동 생성된 키 가져오기
	private Integer leaveapp_no;
}
