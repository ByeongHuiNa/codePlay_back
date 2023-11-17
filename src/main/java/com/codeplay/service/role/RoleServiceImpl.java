package com.codeplay.service.role;

import java.util.ArrayList;
import java.util.List;

import com.codeplay.domain.Attendance_ManagerVo;
import com.codeplay.domain.role.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.User_RoleVo;
import com.codeplay.mapper.role.RoleMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<RoleQueryListDto> getUserByRoleQuery(RoleQueryDto roleQuery) {
		return roleMapper.findByRoleQueryList(roleQuery);
	}

	@Override
	public RoleUserDetailDto getUserDetailByUserNo(int user_no) {
		RoleUserDetailDto user = new RoleUserDetailDto();
		user.setRole(roleMapper.findByUserNo(user_no));
		for(User_RoleVo role: user.getRole()) {
			if(role.getRole_level()==2) {
				user.setAttend_ma(roleMapper.findAttendMaByUserNo(user_no));				
			}
		}
		return user;
	}

	//기존에 있던 기록 지우고, 새로운 기록 insert
	//TODO: 최적화시, 기존에 있던 기록이 변했는지 확인후, 변하지 않았으면 안바꿈.
	@Override
	@Transactional
	public RoleDeleteDto save(Integer userNo, RoleUserDetailDto request) {
		List<Integer> role_level= roleMapper.deleteByUserNo(userNo);
		log.info("pre_role_level : {}",role_level);
		RoleDeleteDto roleDeleteDto = new RoleDeleteDto();
		roleDeleteDto.setPreRoleLevel(role_level);
		roleDeleteDto.setPostRoleLevel(new ArrayList<>());
		int result = roleMapper.deleteAttendMaByUserNo(userNo);
		log.info("delete 결과 : {}",result);
		for(User_RoleVo role : request.getRole()){
			role.setUser_no(userNo);
			roleMapper.saveUserRole(role);
			log.info("post_role_level에 추가 : {}",role.getRole_level());
			roleDeleteDto.getPostRoleLevel().add(role.getRole_level());
			if(role.getRole_level()==2){
				for(Attendance_ManagerVo attendMa :request.getAttend_ma()) {
					attendMa.setUser_no(userNo);
					roleMapper.saveAttendMa(attendMa);
				}
			}
		}

		return roleDeleteDto;
	}

	@Override
	public List<RoleCountDto> getRoleCount() {
		return roleMapper.findRoleCount();
	}

	
	

}
