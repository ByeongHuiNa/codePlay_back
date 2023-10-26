package com.codeplay.service.userInformation;

import com.codeplay.domain.userInformation.dto.UserInformationDto;


public interface UserInformationService {
	
	//Join 없이 데이터 가져옴.
	public UserInformationDto getUserData(int user_no);
	
	

}
