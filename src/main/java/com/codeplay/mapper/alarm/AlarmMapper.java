package com.codeplay.mapper.alarm;

import com.codeplay.domain.AlarmVo;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmMapper {
	List<AlarmVo> findAll(int user_no);
    void insert(AlarmVo alarmVo);
    void updateStatus(int alarm_no);
    void delete(int alarm_no);

}
