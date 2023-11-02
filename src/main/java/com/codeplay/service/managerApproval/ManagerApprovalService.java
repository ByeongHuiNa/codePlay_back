package com.codeplay.service.managerApproval;

import java.util.List;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;

public interface ManagerApprovalService {
	// 로그인 한 근태담당자의 휴가 결재 내역 가져오기  (1차 결재자 승인 전에는 2차 결재자에게 보여지지 않는다.)
	public List<ApprovalLeaveResponseVo> getLeaveApprovalByUserNo(int user_no);
	// 로그인 한 근태담당자의 출퇴근 결재 내역 가져오기
	public List<ApprovalAttendResponseVo> getAttendApprovalByUserNo(int user_no);
}
