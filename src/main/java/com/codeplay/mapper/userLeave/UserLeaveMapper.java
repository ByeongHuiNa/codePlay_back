package com.codeplay.mapper.userLeave;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.LeaveVo;
import com.codeplay.domain.Leave_ApprovalVo;

@Mapper
//휴가보유현황
public interface UserLeaveMapper {
	
	public LeaveVo getUserLeave(int user_no);
	
	public Leave_ApprovalVo getUserLeaveRequest(int user_no);

}
