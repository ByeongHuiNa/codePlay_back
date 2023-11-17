package com.codeplay.service.overTime;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.OvertimeVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.overTime.dto.OvertimeDto;
import com.codeplay.domain.overTime.vo.RequestOvertimeVo;
import com.codeplay.domain.overTime.vo.ResponseOvertimeVo;

import java.util.Date;
import java.util.List;


public interface OverTimeService {
	int app(RequestOvertimeVo vo);

	List<ResponseOvertimeVo> getOverTimeList(OvertimeDto dto);

	void delete(RequestOvertimeVo vo);
}
