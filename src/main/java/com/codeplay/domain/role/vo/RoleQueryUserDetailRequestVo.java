package com.codeplay.domain.role.vo;

import com.codeplay.domain.role.dto.RoleUserDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryUserDetailRequestVo {
	private Integer user_no;
	private RoleUserDetailDto role;

}
