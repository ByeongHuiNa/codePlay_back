package com.codeplay.domain.userInformation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationRequestVo {
	private String user_profile;
	private String user_phone;
	private String user_address;

}
