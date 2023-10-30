package com.codeplay.domain.role.vo;

import java.util.List;

import com.codeplay.domain.Attendance_ManagerVo;
import com.codeplay.domain.User_RoleVo;
import com.codeplay.domain.role.dto.RoleUserDetailDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryUserDetailResponseVo {
	private Integer user_no;
	private RoleUserDetailDto role;

}
