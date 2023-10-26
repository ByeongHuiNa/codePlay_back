package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//검색에서 사용할 객체
public class CriteriaVo {
	private String input;
	private int page;
	private int limit;

}
