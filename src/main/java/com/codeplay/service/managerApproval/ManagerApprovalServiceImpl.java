package com.codeplay.service.managerApproval;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeplay.domain.PolicyVo;
import com.codeplay.domain.managerApproval.dto.ApprovalAttendRequestDto;
import com.codeplay.domain.managerApproval.dto.AttendPolicyDto;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.mapper.managerApproval.ManagerApprovalMapper;
import com.codeplay.mapper.userLeave.UserLeaveMapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManagerApprovalServiceImpl implements ManagerApprovalService {
	@Autowired
	ManagerApprovalMapper managerApprovalMapper;
	
	@Autowired
	UserLeaveMapper userLeaveMapper;

	@Override
	public List<ApprovalLeaveResponseVo> getLeaveApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findLeaveApprovalByUserNo(user_no);
	}

	@Override
	public List<ApprovalAttendResponseVo> getAttendApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findAttendApprovalByUserNo(user_no);
	}

	@Transactional
	@Override // 근태담당자의 휴가 결재 처리 1차
	public void updateFirstLeaveApproval(ApprovalRequestVo vo) {
			if(vo.getLeaveapp_type() == 4) { // 휴가취소
				managerApprovalMapper.updateLeaveCancelApproval(vo);
				if(vo.getLeaveappln_status() == 0) { // 승인
					userLeaveMapper.deleteLeaveRequestByAppNo(vo.getLeaveapp_cancel_no());
					managerApprovalMapper.updateLeave(vo.getUser_no(), -vo.getLeaveapp_total());
				} 
			} else {				
				int result = managerApprovalMapper.updateFirstLeaveApproval(vo); // 휴가신청
				log.info("result : " + result);
			}
	}

	@Transactional
	@Override // 근태담당자의 휴가 결재 처리 2차
	public void updateSecondLeaveApproval(ApprovalRequestVo vo) {
		if(vo.getLeaveappln_status() == 0) { // 승인
			int user_no = vo.getUser_no();
			double leaveapp_total = vo.getLeaveapp_total();
			managerApprovalMapper.updateLeave(user_no, leaveapp_total);
		}
		managerApprovalMapper.updateSecondLeaveApproval(vo);
	}

	@Transactional
	@Override // 근태담당자의 출퇴근 수정 결재 처리
	public void updateAttendApproval(ApprovalAttendRequestVo vo) {
		// 수정 요청자의 정규 출퇴근 시간
		AttendPolicyDto apdto = managerApprovalMapper.findAttendTimeByUserNo(vo.getUser_no());
		managerApprovalMapper.updateAttendApproval(vo); // 출퇴근 수정 결재 테이블 
		if(vo.getAttendapp_status() == 0) { // 결재 승인 처리인 경우 출퇴근 테이블 수정
			ApprovalAttendRequestDto dto  = new ApprovalAttendRequestDto();
			dto.setAttend_no(vo.getAttend_no());
			dto.setUser_no(vo.getUser_no());
			dto.setAttend_start(vo.getAttend_start());
			dto.setAttend_end(vo.getAttend_end());
			dto.setAttend_date(vo.getAttend_date());
			dto.setAttendapp_no(vo.getAttendapp_no());
			dto.setAttendapp_status(vo.getAttendapp_status());
			dto.setAttendapp_reason(vo.getAttendapp_reason());
			dto.setAttendedit_kind(vo.getAttendedit_kind());
			dto.setAttendedit_start_time(vo.getAttendedit_start_time());
			dto.setAttendedit_end_time(vo.getAttendedit_end_time());
			dto.setStandard_start_time(apdto.getStandard_start_time());
			dto.setStandard_end_time(apdto.getStandard_end_time());
			log.info(dto.toString());
			log.info(apdto.toString());
			managerApprovalMapper.updateAttend(dto);
		}
	}
}
