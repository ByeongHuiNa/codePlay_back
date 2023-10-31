package com.codeplay.mapper.access;

import com.codeplay.domain.Attendance_ManagerVo;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.User_RoleVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.role.dto.RoleCountDto;
import com.codeplay.domain.role.dto.RoleQueryDto;
import com.codeplay.domain.role.dto.RoleQueryListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessMapper {
	List<RoleAccessPageDto> findByRoleLevel(int roleLevel);
    RoleAccessCountResponseVo findCustomAccessCount();

    List<CustomUserListDto> findCustomUserList(CriteriaVo cri);

    List<CustomAccessPageDto> findByUserNo(int user_no);
}
