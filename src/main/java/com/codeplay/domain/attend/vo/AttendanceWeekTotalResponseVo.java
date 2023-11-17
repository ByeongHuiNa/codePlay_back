package com.codeplay.domain.attend.vo;

import java.sql.Time;
import java.util.Date;

import com.codeplay.domain.AttendanceVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceWeekTotalResponseVo {
	
private Integer attend_no;
	
	
	private String attend_total;
	


}
