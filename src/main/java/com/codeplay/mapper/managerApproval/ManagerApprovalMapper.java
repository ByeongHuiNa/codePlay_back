package com.codeplay.mapper.managerApproval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;

@Mapper
public interface ManagerApprovalMapper {
	// 로그인 한 근태담당자의 휴가 결재 내역 가져오기 (1차 결재자 승인 전에는 2차 결재자에게 보여지지 않는다.)
	public List<ApprovalLeaveResponseVo> findLeaveApprovalByUserNo(int user_no);
	// 로그인 한 근태담당자의 출퇴근 결재 내역 가져오기
	public List<ApprovalAttendResponseVo> findAttendApprovalByUserNo(int user_no);
}
