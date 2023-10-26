package com.codeplay.mapper.userAttend;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.AttendanceVo;

@Mapper
public interface UserAttendMapper {
	// user_no 사용하여 전체 출퇴근(근태) 내역 가져오기
	public AttendanceVo findAttendByUserNo(int user_no);
}
