package com.codeplay.domain.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCountDto {
	 private Integer role_level;
	 private String role_name;
	 private Integer count;

}
