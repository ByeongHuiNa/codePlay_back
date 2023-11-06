package com.codeplay.mapper.managerApproval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;

@Mapper
public interface ManagerApprovalMapper {
	// 로그인 한 근태담당자의 휴가 결재 내역 가져오기 (1차 결재자 승인 전에는 2차 결재자에게 보여지지 않는다.)
	public List<ApprovalLeaveResponseVo> findLeaveApprovalByUserNo(int user_no);
	// 로그인 한 근태담당자의 출퇴근 결재 내역 가져오기
	public List<ApprovalAttendResponseVo> findAttendApprovalByUserNo(int user_no);
	// 근태담당자의 휴가 결재 처리 1차 (승인,반려) : 휴가신청
	public int updateFirstLeaveApproval(ApprovalRequestVo vo);
	// 근태담당자의 휴가 취소 결재 처리 1차 (승인,반려) : 휴가취소신청
	public int updateLeaveCancelApproval(ApprovalRequestVo vo);
	// 근태담당자의 휴가 결재 처리 2차 (승인,반려) : 휴가신청
	public int updateSecondLeaveApproval(ApprovalRequestVo vo);
	// 근태담당자의 휴가 결재 승인 시 사용자의 휴가 차감 (휴가취소 신청의 경우 증가)
	public int updateLeave(int user_no, double leaveapp_total);
}
