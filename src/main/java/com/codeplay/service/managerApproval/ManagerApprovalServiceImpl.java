package com.codeplay.service.managerApproval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.mapper.managerApproval.ManagerApprovalMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManagerApprovalServiceImpl implements ManagerApprovalService {
	@Autowired
	ManagerApprovalMapper managerApprovalMapper;

	@Override
	public List<ApprovalLeaveResponseVo> getLeaveApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findLeaveApprovalByUserNo(user_no);
	}

	@Override
	public List<ApprovalAttendResponseVo> getAttendApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findAttendApprovalByUserNo(user_no);
	}
}
