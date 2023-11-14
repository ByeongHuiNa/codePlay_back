package com.codeplay.domain.overTime.vo;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.OvertimeVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//초과근무 테이블
public class ResponseOvertimeVo {

	private Date overtime_date;
	private Date attend_date;
	private Time attend_start;
	private Time attend_end;
	private Time overtime_time;
	private String overtime_content;
}
