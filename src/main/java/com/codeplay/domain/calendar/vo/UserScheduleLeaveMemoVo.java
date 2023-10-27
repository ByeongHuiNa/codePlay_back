package com.codeplay.domain.calendar.vo;

import java.util.Date;

import com.codeplay.domain.UserVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserScheduleLeaveMemoVo {
	
	private Integer schedule_memo_no;
	
	private Integer schedule_no;
	
	private Integer leave_no;
	
	private Integer schedule_memo_writer;
	
	private Date schedule_memo_create;
	
	private String schedule_memo_content;
	
	private UserVo user;

}

