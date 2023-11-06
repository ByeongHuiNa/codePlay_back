package com.codeplay.service.managerModify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.managerModify.vo.UserListResponseVo;
import com.codeplay.mapper.managerModify.ManagerModifyMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MangerModifyServiceImpl implements ManagerModifyService {
	@Autowired
	ManagerModifyMapper managerModifyMapper;

	@Override // 사용자와 같은 부서의 전체 직원 목록 가져오기
	public List<UserListResponseVo> getAllUsersByUserNo(int user_no) {
		int dept_no = managerModifyMapper.findDeptNoByUserNo(user_no);
		return managerModifyMapper.findAllUsersByDeptNo(dept_no);
	}

}
