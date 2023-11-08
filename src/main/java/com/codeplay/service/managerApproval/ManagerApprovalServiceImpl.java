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
			// 수정 내용에 따라서 attend_start, attend_end 지정
			if(vo.getAttendedit_kind() == 0) { // 0. 출근만 수정
				dto.setAttend_start(vo.getAttendedit_start_time()); 
				dto.setAttend_end(vo.getAttend_end());
			} else if(vo.getAttendedit_kind() == 1) { // 1. 퇴근만 수정
				dto.setAttend_start(vo.getAttend_start());
				dto.setAttend_end(vo.getAttendedit_end_time());
			} else { // 2. 출근과 퇴근 수정				
				dto.setAttend_start(vo.getAttendedit_start_time()); 			
				dto.setAttend_end(vo.getAttendedit_end_time());
			}
			// 수정된 출퇴근 시간에 따라서 attend_total, attend_status 지정
			if(dto.getAttend_start() != null && dto.getAttend_end() != null) {
				if(apdto.getStandard_start_time().compareTo(dto.getAttend_start()) == -1 
						&& apdto.getStandard_end_time().compareTo(dto.getAttend_end()) != 1) {
					// 출근시간이 정책 시간보다 늦고 퇴근 시간은 정책 시간보다 빠르지 않다 (지각)
					int totalSecond = (dto.getAttend_end().getHour() * 3600 + dto.getAttend_end().getMinute() * 60
							+ dto.getAttend_end().getSecond()) - (dto.getAttend_start().getHour() * 3600
									+ dto.getAttend_start().getMinute() * 60 + dto.getAttend_start().getSecond());
					LocalTime totalTime = LocalTime.of(totalSecond/3600 - 1, totalSecond%3600/60, totalSecond%3600%60);
					dto.setAttend_total(totalTime);
					dto.setAttend_status("지각");
				} else if(apdto.getStandard_start_time().compareTo(dto.getAttend_start()) != -1
						&& apdto.getStandard_end_time().compareTo(dto.getAttend_end()) == 1) {
					// 출근시간이 정책 시간보다 늦지 않고 퇴근 시간은 정책 시간보다 빠르다. (조퇴)
					int totalSecond = (dto.getAttend_end().getHour() * 3600 + dto.getAttend_end().getMinute() * 60
							+ dto.getAttend_end().getSecond()) - (dto.getAttend_start().getHour() * 3600
									+ dto.getAttend_start().getMinute() * 60 + dto.getAttend_start().getSecond());
					LocalTime totalTime = LocalTime.of(totalSecond/3600 - 1, totalSecond%3600/60, totalSecond%3600%60);
					dto.setAttend_total(totalTime);
					dto.setAttend_status("조퇴");
				} else if(apdto.getStandard_start_time().compareTo(dto.getAttend_start()) != -1 
						&& apdto.getStandard_end_time().compareTo(dto.getAttend_end()) != 1) {
					// 출근시간이 정책 시간보다 늦지 않고 퇴근 시간도 정책 시간보다 빠르지 않다 (정상)
					dto.setAttend_total(LocalTime.of(8, 0, 0));
					dto.setAttend_status("정상");
				} else {
					// 출근시간이 정책 시간보다 늦고 퇴근 시간도 정책 시간보다 빠름 (지각, 조퇴 -> 결근으로 처리)
					dto.setAttend_total(LocalTime.of(0, 0, 0));
					dto.setAttend_status("결근");
				}
			} else { // 출근시간 혹은 퇴근시간 중 하나라도 null 값일 때는 결근
				dto.setAttend_total(LocalTime.of(0, 0, 0));
				dto.setAttend_status("결근");
			}
			dto.setAttend_date(vo.getAttend_date());
			dto.setAttendapp_no(vo.getAttendapp_no());
			dto.setAttendapp_status(vo.getAttendapp_status());
			dto.setAttendedit_kind(vo.getAttendedit_kind());
			dto.setStandard_start_time(apdto.getStandard_start_time());
			dto.setStandard_end_time(apdto.getStandard_end_time());
			log.info(dto.toString());
			log.info(apdto.getStandard_start_time().compareTo(dto.getAttend_start()) + "");
			log.info(apdto.getStandard_end_time().compareTo(dto.getAttend_end()) + "");
			managerApprovalMapper.updateAttend(dto);
		}
	}
}
