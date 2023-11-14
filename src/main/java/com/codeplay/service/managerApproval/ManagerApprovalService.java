package com.codeplay.service.managerApproval;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalOvertimeResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.domain.managerApproval.vo.DeptLeaveRequestVo;

public interface ManagerApprovalService {
	// 로그인 한 근태담당자의 휴가 결재 내역 가져오기  (1차 결재자 승인 전에는 2차 결재자에게 보여지지 않는다.)
	public List<ApprovalLeaveResponseVo> getLeaveApprovalByUserNo(int user_no);
	// 로그인 한 근태담당자의 출퇴근 결재 내역 가져오기
	public List<ApprovalAttendResponseVo> getAttendApprovalByUserNo(int user_no);
	// 근태담당자의 휴가 결재 처리 1차 (승인,반려) : 휴가신청, 휴가취소신청
	public void updateFirstLeaveApproval(ApprovalRequestVo vo);
	// 근태담당자의 휴가 결재 처리 2차 (승인,반려) : 휴가신청, 휴가취소신청
	public void updateSecondLeaveApproval(ApprovalRequestVo vo);
	// 근태담당자의 출퇴근 수정 결재 처리
	public void updateAttendApproval(ApprovalAttendRequestVo vo);
	// 근태담당자의 출퇴근 total, status 판단
	public HashMap<Integer, Object> attendConfirm(LocalTime start, LocalTime standard_start, LocalTime end, LocalTime standard_end, int half_leave);
	// 특정 날짜에 휴가를 사용한 같은 부서의 직원 수
	public HashMap<Integer, Object> getDeptLeaveByUserNo(DeptLeaveRequestVo vo);
	// 근태담당자의 초과근무 결재 리스트 조회
	public List<ApprovalOvertimeResponseVo> getOvertimeApprovalByUserNo(int user_no);
}
