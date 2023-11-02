package com.codeplay.service.role;

import java.util.List;

import com.codeplay.domain.Attendance_ManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.User_RoleVo;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import com.codeplay.domain.role.dto.RoleUserDetailDto;
import com.codeplay.mapper.role.RoleMapper;

import lombok.extern.slf4j.Slf4j;

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
	public void save(Integer userNo, RoleUserDetailDto request) {
		roleMapper.deleteByUserNo(userNo);
		roleMapper.deleteAttendMaByUserNo(userNo);
		for(User_RoleVo role : request.getRole()){
			role.setUser_no(userNo);
			roleMapper.saveUserRole(role);
		}
		if(request.getAttend_ma() != null){
			for(Attendance_ManagerVo attendMa :request.getAttend_ma()) {
				attendMa.setUser_no(userNo);
				roleMapper.saveAttendMa(attendMa);
			}
		}
		
	}

	@Override
	public List<RoleCountDto> getRoleCount() {
		return roleMapper.findRoleCount();
	}

	
	

}
