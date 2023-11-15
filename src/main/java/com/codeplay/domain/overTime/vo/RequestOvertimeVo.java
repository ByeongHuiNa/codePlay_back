package com.codeplay.domain.overTime.vo;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.OvertimeVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//초과근무 테이블
public class RequestOvertimeVo {
	OvertimeVo overtimeVo;
	AttendanceVo attendanceVo;
}
