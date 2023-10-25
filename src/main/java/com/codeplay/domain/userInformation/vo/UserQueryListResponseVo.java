package com.codeplay.domain.userInformation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryListResponseVo {
	private Integer user_no;
	private String dept_name;
	private String user_name;
	private String user_position;

}
