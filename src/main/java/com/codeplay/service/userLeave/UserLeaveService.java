package com.codeplay.service.userLeave;

import java.util.List;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.dto.UserLeaveCancelRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveLineRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveRequestDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.leave.vo.UserLeaveResponseVo;

public interface UserLeaveService {
	// 사용자의 현재 휴가 보유 현황
	public LeaveVo getLeave(int user_no);
	// 사용자의 월별 휴가 신청내역(대기 상태)
	public List<Leave_WaitDto> getUserLeaveWait(int user_no, int month); 
	// 사용자의 월별 휴가 신청내역(결재진행중, 결재완료-승인, 결재완료-반려 상태)
	public List<UserLeaveApprovalLineDto> getLeaveRequest(int user_no, int month);
	// 사용자의 최근 휴가 신청내역(메인페이지)
	public List<UserLeaveApprovalLineDto> getUserRecentLeaveRequest(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재 대기 상태)
	public List<UserLeaveResponseVo> getAwaitLeaveRequestByUserNo(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재진행중, 결재완료-승인, 결재완료-반려 상태)
	public List<UserLeaveResponseVo> getRecentLeaveRequestByUserNo(int user_no);
	// 사용자의 결재대기 중인 신청 휴가 취소 처리
	public int removeLeaveRequestByAppNo(int leaveapp_no);
	// 사용자의 휴가 신청 + 결재선
	public void addLeaveRequest(UserLeaveRequestDto dto, UserLeaveLineRequestDto dtoFirstLine, UserLeaveLineRequestDto dtoSecondLine);
	// 사용자의 휴가 취소 신청
	public void addLeaveCancelRequest(UserLeaveCancelRequestDto dto, UserLeaveLineRequestDto dtoFirstLine);
}
