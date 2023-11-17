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
import com.codeplay.mapper.access.AccessMapper;
import com.codeplay.mapper.overTime.OverTimeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OverTimeServiceImpl implements OverTimeService {
	@Autowired
	OverTimeMapper overTimeMapper;
	@Override
	@Transactional
	public int app(RequestOvertimeVo vo) {
		vo.getOvertimeVo().setAttend_no(overTimeMapper.insertAttendance(vo.getAttendanceVo()));
		return overTimeMapper.insertOvertime(vo.getOvertimeVo());
	}

	@Override
	public List<ResponseOvertimeVo> getOverTimeList(OvertimeDto dto) {
		return overTimeMapper.getOverTimeList(dto);
	}

	@Override
	public void delete(RequestOvertimeVo vo) {
		overTimeMapper.deleteOvertime(vo.getOvertimeVo().getOvertime_no());
		overTimeMapper.deleteAttendance(vo.getAttendanceVo().getAttend_no());
	}
}
