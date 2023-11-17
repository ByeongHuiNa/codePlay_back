package com.codeplay.domain.role.dto;

import com.codeplay.domain.CriteriaVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDeleteDto {
	private List<Integer> preRoleLevel;
	private List<Integer> postRoleLevel;



}
