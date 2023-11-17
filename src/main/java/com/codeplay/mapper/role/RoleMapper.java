package com.codeplay.mapper.role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.Attendance_ManagerVo;
import com.codeplay.domain.User_RoleVo;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;

@Mapper
public interface RoleMapper {
	public List<RoleQueryListDto> findByRoleQueryList(RoleQueryDto roleQuery);
	public List<User_RoleVo> findByUserNo(int user_no);
	public List<Integer> deleteByUserNo(int user_no);
	public Integer deleteAttendMaByUserNo(int user_no);
	public List<Attendance_ManagerVo> findAttendMaByUserNo(int user_no);
	public List<RoleCountDto> findRoleCount();

	void saveUserRole(User_RoleVo role);

	void saveAttendMa(Attendance_ManagerVo attendMa);
}
