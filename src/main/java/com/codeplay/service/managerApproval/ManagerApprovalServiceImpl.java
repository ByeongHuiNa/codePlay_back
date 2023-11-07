package com.codeplay.service.managerApproval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
