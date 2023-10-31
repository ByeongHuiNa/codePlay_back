package com.codeplay.mapper.userAttend;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.attend.dto.UserAttendEditDto;
import com.codeplay.domain.attend.vo.UserAttendEditResponseVo;

@Mapper
public interface UserAttendEditMapper {
	// 사용자의 전체 출퇴근(근태) 수정 내역 가져오기 : user_no 사용
	public List<UserAttendEditResponseVo> findAttendEditByUserNo(int user_no);
	// 사용자의 출퇴근 수정하기 : attendance_edit_approval 테이블에 저장
	public int saveAttendEdit(UserAttendEditDto dto);
}
