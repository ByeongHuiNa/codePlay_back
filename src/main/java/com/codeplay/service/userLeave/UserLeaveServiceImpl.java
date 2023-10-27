package com.codeplay.service.userLeave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;

import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;
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
	public List<Leave_WaitDto> getUserLeaveWait(int user_no) {
		log.info("get LeaveRequestWait");
		return leaveMapper.getUserLeaveWait(user_no);
	}

	@Override
	public List<UserLeaveApprovalLineDto> getLeaveRequest2(int user_no) {
		
		return leaveMapper.getUserLeaveRequest2(user_no);
	}

}
