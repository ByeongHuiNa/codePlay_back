package com.codeplay.service.userInformation;

import java.util.List;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
import com.codeplay.domain.userInformation.vo.DeptListResponseVo;


public interface UserInformationService {
	
	//Join 없이 데이터 가져옴.
	public UserInformationDto getUserData(int user_no);
	//Join 해서 데이터 가져옴.
	public List<UserQueryDto> getAllUser();
	public List<UserQueryDto> getUserByUsername(CriteriaVo cri);
	//User Table 수정하는 서비스
	public void patchUser(UserInformationPatchDto userPatch);

	//Dept 전체 가져오는 서비스
    List<DeptListResponseVo> getAllDept();
}
