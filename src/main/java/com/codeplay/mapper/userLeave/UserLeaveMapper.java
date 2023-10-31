package com.codeplay.mapper.userLeave;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.dto.UserLeaveCancelRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveLineRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveRequestDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.leave.vo.UserLeaveResponseVo;

@Mapper

public interface UserLeaveMapper {
	// 사용자의 현재 휴가 보유 현황
	public LeaveVo getUserLeave(int user_no);
	
	public List<Leave_WaitDto> getUserLeaveWait(int user_no);
	
	public List<UserLeaveApprovalLineDto> getUserLeaveRequest2(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재 대기)
	public List<UserLeaveResponseVo> findAwaitLeaveRequestByUserNo(int user_no);
	// 사용자의 전체 휴가 신청 내역 (결재진행중, 결재완료-승인, 결재완료-반려)
	public List<UserLeaveResponseVo> findRecentLeaveRequestByUserNo(int user_no);
	// 사용자의 결재대기 중인 신청 휴가 취소 
	public int deleteLeaveRequestByAppNo(int leaveapp_no);
	// 사용자의 휴가 신청
	public int saveLeaveRequest(UserLeaveRequestDto dto);
	// 사용자의 휴가 취소 신청
	public int saveLeaveCancelRequest(UserLeaveCancelRequestDto dto);
	// 사용자의 휴가 신청 결재선
	public int saveLeaveLineRequest(UserLeaveLineRequestDto dto);
}
