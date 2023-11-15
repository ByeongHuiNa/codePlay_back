package com.codeplay.domain.managerApproval.vo;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalOvertimeResponseVo {
	private Integer overtime_no;
	private Integer attend_no;
	private Integer user_no;
	private String user_name;
	private Time attend_start;
	private Time attend_end;
	private Time attend_total;
	private Date attend_date;
	private Integer overtime_type;
	private Time overtime_time;
	private String overtime_content;
	private String overtime_reason;
	private Date overtime_date;
	private Integer overtimeapp_status;
	private Integer overtimeapp_user_no;
	private String overtimeapp_reason;
	private Date overtimeapp_date;
}
