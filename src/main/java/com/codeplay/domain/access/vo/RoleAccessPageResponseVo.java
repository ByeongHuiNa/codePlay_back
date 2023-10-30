package com.codeplay.domain.access.vo;

import com.codeplay.domain.Access_PageVo;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAccessPageResponseVo {
	 private List<RoleAccessPageDto> access_page_list;
}

