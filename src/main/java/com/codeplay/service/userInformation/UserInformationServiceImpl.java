package com.codeplay.service.userInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.controller.UserInformationController;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.mapper.userInformation.DeptAssignMapper;
import com.codeplay.mapper.userInformation.DeptMapper;
import com.codeplay.mapper.userInformation.UserMapper;

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


	@Override
	public UserInformationDto getUserData(int user_no) {
		UserInformationDto user = new UserInformationDto();
		user.setUser(userMapper.findByUserNo(user_no));
		// TODO: user가 여러부서를 갖는다면 아래 결과값이 에러남.
		user.setUserDept(deptAssignMapper.findByUserNo(user_no));
		user.setDept(deptMapper.findByDeptNo(user.getUserDept().getDept_no()));
		return user;
	}

}
