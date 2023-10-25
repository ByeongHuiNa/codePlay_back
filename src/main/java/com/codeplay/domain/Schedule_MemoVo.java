package com.codeplay.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule_MemoVo {
	
	private Integer schedule_memo_no;
	
	private Integer schedule_no;
	
	private String schedule_meno_writer;
	
	private Date schedule_memo_create;
	
	private String schedule_memo_content;
	

}
