package com.codeplay.domain.access.vo;

import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessPageListResponseVo {
	 private List<CustomAccessPageDto> access_page_list;
}

