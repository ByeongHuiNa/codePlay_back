package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//검색에서 사용할 객체
public class CriteriaVo {

	private String input;
	private int page;
	private int limit;
	private Date start_date;
	private Date end_date;

	public CriteriaVo(String input, int page, int limit) {
		this.input = input;
		this.page = page;
		this.limit = limit;
	}
}
