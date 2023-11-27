package com.codeplay.domain.leave.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // 휴가 신청
public class UserLeaveRequestDto {
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
	// selectKey로 자동 생성된 키 가져오기
	private Integer leaveapp_no;
	
	public UserLeaveRequestDto(Integer user_no, String leaveapp_title, String leaveapp_content, Date leaveapp_start,
			Date leaveapp_end, Double leaveapp_total, Integer leaveapp_type) {
		super();
		this.user_no = user_no;
		this.leaveapp_title = leaveapp_title;
		this.leaveapp_content = leaveapp_content;
		this.leaveapp_start = leaveapp_start;
		this.leaveapp_end = leaveapp_end;
		this.leaveapp_total = leaveapp_total;
		this.leaveapp_type = leaveapp_type;
	}
	
	
}
