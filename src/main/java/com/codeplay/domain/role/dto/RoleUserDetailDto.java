package com.codeplay.domain.role.dto;

import java.util.Date;
import java.util.List;

import com.codeplay.domain.Attendance_ManagerVo;
import com.codeplay.domain.User_RoleVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDetailDto {
	private List<User_RoleVo> role;
	private List<Attendance_ManagerVo> attend_ma;

}
