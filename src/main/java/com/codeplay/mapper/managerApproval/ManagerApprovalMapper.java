package com.codeplay.mapper.managerApproval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.OvertimeVo;
import com.codeplay.domain.managerApproval.dto.AddAttendDto;
import com.codeplay.domain.managerApproval.dto.ApprovalAttendRequestDto;
import com.codeplay.domain.managerApproval.dto.AttendPolicyDto;
import com.codeplay.domain.managerApproval.dto.DeptLeaveRequestDto;
import com.codeplay.domain.managerApproval.dto.HalfLeaveDto;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalOvertimeResponseVo;
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
	// 사용자의 잔여휴가 수정 (근태담당자가 휴가 승인 시 처리할 내용)
	public int updateLeave(int user_no, double leaveapp_total);
	// 근태담당자의 휴가 결재 승인 시 출퇴근 테이블에 등록
	public int saveLeaveToAttendance(AddAttendDto dto);
	// 사용자의 잔여휴가 조회
	public LeaveVo findLeaveByUserNo(int user_no);
	// 수정 요청자의 정규 출퇴근 시간 조회
	public AttendPolicyDto findAttendTimeByUserNo(int user_no); 
	// 근태담당자의 출퇴근 수정 결재 시 출퇴근 결재 테이블 수정 처리
	public int updateAttendApproval(ApprovalAttendRequestVo vo);
	// 근태담당자의 출퇴근 수정 결재 승인 시 출퇴근 테이블 수정 처리
	public int updateAttend(ApprovalAttendRequestDto dto);
	// 출퇴근 상태 판단 시 반차(휴가) 확인
	public int findHalfLeaveByUserNo(HalfLeaveDto dto);
	// 특정 날짜에 휴가를 사용한 같은 부서의 직원 수
	public int findDeptLeaveByUserNo(DeptLeaveRequestDto dto);
	// 같은 부서의 총 직원 수
	public int findDeptUsersByUserNo(int user_no);
	// 휴가 2차 결재자의 번호 조회하기
	public int findSecondAppByLeaveappNo(int leaveapp_no);
	// 근태담당자의 초과근무 결재 리스트 조회
	public List<ApprovalOvertimeResponseVo> findOvertimeApprovalByUserNo(int user_no);
	// 근태담당자의 결재 승인 시 초과근무 수정 처리
	public int updateOvertimeApproval(OvertimeVo vo);
	// 근태 번호로 근태 데이터 가져오기
	public AttendanceVo findAttendByAttendNo(int attend_no);
}
