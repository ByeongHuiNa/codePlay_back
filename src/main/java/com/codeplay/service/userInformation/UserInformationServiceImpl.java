package com.codeplay.service.userInformation;

import java.util.ArrayList;
import java.util.List;

import com.codeplay.domain.userInformation.vo.DeptListResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
import com.codeplay.mapper.userInformation.DeptAssignMapper;
import com.codeplay.mapper.userInformation.DeptMapper;
import com.codeplay.mapper.userInformation.UserMapper;
import com.codeplay.mapper.userInformation.UserQueryMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	DeptMapper deptMapper;
	
	@Autowired
	DeptAssignMapper deptAssignMapper;

	@Autowired
	UserQueryMapper userQueryMapper;


	@Override
	public UserInformationDto getUserData(int user_no) {
		UserInformationDto user = new UserInformationDto();
		user.setUser(userMapper.findByUserNo(user_no));
		// TODO: user가 여러부서를 갖는다면 아래 결과값이 에러남.
		user.setUserDept(deptAssignMapper.findByUserNo(user_no));
		user.setDept(deptMapper.findByDeptNo(user.getUserDept().getDept_no()));
		return user;
	}


	@Override
	public List<UserQueryDto> getAllUser() {
		return userQueryMapper.findAll();
	}


	@Override
	public List<UserQueryDto> getUserByUsername(CriteriaVo cri) {
		
		return userQueryMapper.findByUsername(cri);
	}


	@Override
	public void patchUser(UserInformationPatchDto userPatch) {
		userMapper.save(userPatch);
		
	}

	@Override
	public List<DeptListResponseVo> getAllDept() {
		return deptMapper.findAll();
	}


}
