package com.codeplay.mapper.overTime;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.OvertimeVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.overTime.dto.OvertimeDto;
import com.codeplay.domain.overTime.vo.RequestOvertimeVo;
import com.codeplay.domain.overTime.vo.ResponseOvertimeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface OverTimeMapper {

    Integer insertAttendance(AttendanceVo attendanceVo);
    void insertOvertime(OvertimeVo overtimeVo);

    List<ResponseOvertimeVo> getOverTimeList(OvertimeDto dto);

    void deleteOvertime(Integer overtime_no);
    void deleteAttendance(Integer attend_no);
}
