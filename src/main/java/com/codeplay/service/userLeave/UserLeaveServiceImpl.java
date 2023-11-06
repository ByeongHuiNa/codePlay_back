package com.codeplay.service.userLeave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.DeptVo;
import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;

import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.dto.UserLeaveCancelRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveLineRequestDto;
import com.codeplay.domain.leave.dto.UserLeaveRequestDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
import com.codeplay.domain.leave.vo.UserLeaveResponseVo;
import com.codeplay.domain.leave.vo.UsersLeaveCountVo;
import com.codeplay.mapper.userLeave.UserLeaveMapper;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserLeaveServiceImpl implements UserLeaveService {
	@Autowired
	UserLeaveMapper leaveMapper;
	
	@Override
	public LeaveVo getLeave(int user_no) {
		log.info("get Leave");
		return leaveMapper.getUserLeave(user_no);
	}
	
	@Override
	public List<UserLeaveApprovalLineDto> getUserLeaveWait(int user_no, int month) {
		log.info("get LeaveRequestWait");
		return leaveMapper.getUserLeaveWait(user_no, month);
	}

	@Override
	public List<UserLeaveApprovalLineDto> getLeaveRequest(int user_no, int month) {
		return leaveMapper.getUserLeaveRequest(user_no, month);
	}

	@Override // 사용자의 전체 휴가 신청 내역 (결재 대기 상태)
	public List<UserLeaveResponseVo> getAwaitLeaveRequestByUserNo(int user_no) {
		return leaveMapper.findAwaitLeaveRequestByUserNo(user_no);
	}

	@Override // 사용자의 전체 휴가 신청 내역 (결재진행중, 결재완료-승인, 결재완료-반려 상태)
	public List<UserLeaveResponseVo> getRecentLeaveRequestByUserNo(int user_no) {
		return leaveMapper.findRecentLeaveRequestByUserNo(user_no);
	}

	@Override // 사용자의 결재대기 중인 신청 휴가 취소 처리
	public int removeLeaveRequestByAppNo(int leaveapp_no) {
		return leaveMapper.deleteLeaveRequestByAppNo(leaveapp_no);
	}

	@Transactional
	@Override // 사용자의 휴가 신청
	public void addLeaveRequest(UserLeaveRequestDto dto, 
														UserLeaveLineRequestDto dtoFirstLine, 
														UserLeaveLineRequestDto dtoSecondLine) {
		int leaveapp_no = leaveMapper.saveLeaveRequest(dto);
		log.info("leaveapp_no : " + leaveapp_no);
		dtoFirstLine.setLeaveapp_no(leaveapp_no);
		dtoSecondLine.setLeaveapp_no(leaveapp_no);
		leaveMapper.saveLeaveLineRequest(dtoFirstLine);
		leaveMapper.saveLeaveLineRequest(dtoSecondLine);
	}

	@Transactional
	@Override // 사용자의 휴가 취소 신청
	public void addLeaveCancelRequest(UserLeaveCancelRequestDto dto, 
															UserLeaveLineRequestDto dtoFirstLine) {
		int leaveapp_no = leaveMapper.saveLeaveCancelRequest(dto);
		log.info("leaveapp_no : " + leaveapp_no);
		dtoFirstLine.setLeaveapp_no(leaveapp_no);
		leaveMapper.saveLeaveLineRequest(dtoFirstLine);
	}

	@Override //사용자의 최근 휴가신청내역(메인페이지)
	public List<UserLeaveApprovalLineDto> getUserRecentLeaveRequest(int user_no) {	
		return leaveMapper.getUserRecentLeaveRequest(user_no);
	}
	
	//근태담당자의 부서 사원 휴가 현황 조회
	@Override
	public List<UsersLeaveCountVo> getUsersLeave(int dept_no) {
		return leaveMapper.seeUsersLeave(dept_no);
	}

	@Override
	public List<DeptVo> getDept() {
		return leaveMapper.getDept();
	}
}
