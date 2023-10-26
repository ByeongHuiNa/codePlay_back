package com.codeplay.domain.userInformation.dto;

import com.codeplay.domain.DeptVo;
import com.codeplay.domain.Dept_AssignVo;
import com.codeplay.domain.UserVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDto {
	private UserVo User;
	private DeptVo Dept;
	private Dept_AssignVo UserDept;

}
