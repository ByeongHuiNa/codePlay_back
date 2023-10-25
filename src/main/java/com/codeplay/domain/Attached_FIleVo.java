package com.codeplay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//첨부파일 테이블
public class Attached_FIleVo {
	
	private Integer attached_no;
	
	private Integer attached_app_no;
	
	private String attached_kind;
	
	private String attached_type;
	
	private String attached_loc;
	
	private String attached_name;

}
