package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//근태담당자 테이블
public class Attendance_ManagerVo {
	
	private Integer attend_ma_no;
	
	private Integer user_no;
	
	private Integer dept_no;
	
	private Date attend_ma_designated_date;

}
