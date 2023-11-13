package com.codeplay.domain.managerModify.vo;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttendModifyVo {
	private List<Integer> user_no_list;
	private String attend_date;
	private String attendedit_title;
	private Integer attendedit_kind;
	private LocalTime attendedit_start_time;
	private LocalTime attendedit_end_time;
	private Integer attendapp_user_no;
	private Integer attendapp_status;
	private String attendapp_reason;
}
