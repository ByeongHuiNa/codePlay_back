package com.codeplay.domain.role.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCountResponseVo {
	 private Integer role_level;
	 private String role_name;
	 private Integer count;

}
