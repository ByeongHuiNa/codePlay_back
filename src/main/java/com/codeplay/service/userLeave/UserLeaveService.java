package com.codeplay.service.userLeave;

import java.util.List;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.leave.vo.UserLeaveResponseVo;

public interface UserLeaveService {
	// 사용자의 현재 휴가 보유 현황
	public LeaveVo getLeave(int user_no);
	
	public List<Leave_ApprovalVo> getLeaveRequest(int user_no);
	
	public List<UserLeaveApprovalLineDto> getLeaveRequest2(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재 대기 상태)
	public List<UserLeaveResponseVo> getAwaitLeaveRequestByUserNo(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재진행중, 결재완료-승인, 결재완료-반려 상태)
	public List<UserLeaveResponseVo> getRecentLeaveRequestByUserNo(int user_no);
	// 사용자의 결재대기 중인 신청 휴가 취소 처리
	public int removeLeaveRequestByAppNo(int leaveapp_no);
}
