package com.codeplay.domain.role.dto;

import com.codeplay.domain.CriteriaVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryDto {
	private CriteriaVo Criteria;
	private int role_level;

}
