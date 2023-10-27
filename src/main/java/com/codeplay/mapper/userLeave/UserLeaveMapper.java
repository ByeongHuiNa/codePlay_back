package com.codeplay.mapper.userLeave;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.leave.dto.Leave_WaitDto;
import com.codeplay.domain.leave.dto.UserLeaveApprovalLineDto;
import com.codeplay.domain.leave.vo.UserLeaveApprovalLineVo;

@Mapper

public interface UserLeaveMapper {
	
	public LeaveVo getUserLeave(int user_no);
	
	public List<Leave_WaitDto> getUserLeaveWait(int user_no);
	
	public List<UserLeaveApprovalLineDto> getUserLeaveRequest2(int user_no);

}
