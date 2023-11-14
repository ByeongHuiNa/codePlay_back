package com.codeplay.domain.overTime.dto;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.OvertimeVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//초과근무 테이블
public class OvertimeDto {
	Integer user_no;
	CriteriaVo criteriaVo;
}
