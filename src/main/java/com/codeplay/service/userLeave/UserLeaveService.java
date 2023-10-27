package com.codeplay.service.userLeave;

import java.util.List;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;

public interface UserLeaveService {
	
	public LeaveVo getLeave(int user_no);
	
	public List<Leave_WaitDto> getUserLeaveWait(int user_no);
	
	public List<UserLeaveApprovalLineDto> getLeaveRequest2(int user_no);
}
