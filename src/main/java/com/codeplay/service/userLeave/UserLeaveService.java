package com.codeplay.service.userLeave;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;

public interface UserLeaveService {
	
	public LeaveVo getLeave(int user_no);
	
	public Leave_ApprovalVo getLeaveRequest(int user_no);
}
