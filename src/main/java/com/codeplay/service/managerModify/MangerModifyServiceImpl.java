package com.codeplay.service.managerModify;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.managerApproval.dto.ApprovalAttendRequestDto;
import com.codeplay.domain.managerApproval.dto.AttendPolicyDto;
import com.codeplay.domain.managerApproval.dto.HalfLeaveDto;
import com.codeplay.domain.managerModify.dto.UserAttendModifyDto;
import com.codeplay.domain.managerModify.vo.UserAttendModifyVo;
import com.codeplay.domain.managerModify.vo.UserListResponseVo;
import com.codeplay.mapper.managerApproval.ManagerApprovalMapper;
import com.codeplay.mapper.managerModify.ManagerModifyMapper;
import com.codeplay.mapper.userAttend.UserAttendMapper;
import com.codeplay.service.managerApproval.ManagerApprovalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MangerModifyServiceImpl implements ManagerModifyService {
	@Autowired
	ManagerModifyMapper managerModifyMapper;
	
	@Autowired
	UserAttendMapper userAttendMapper;
	
	@Autowired
	ManagerApprovalMapper managerApprovalMapper;
	
	@Autowired
	ManagerApprovalService managerApprovalService;

	@Override // 사용자와 같은 부서의 전체 직원 목록 가져오기
	public List<UserListResponseVo> getAllUsersByUserNo(int user_no) {
		int dept_no = managerModifyMapper.findDeptNoByUserNo(user_no);
		return managerModifyMapper.findAllUsersByDeptNo(dept_no);
	}

	@Override
	public void modifyAttend(UserAttendModifyVo vo) {
		for(int user_no : vo.getUser_no_list()) {
			UserAttendModifyDto dto = new UserAttendModifyDto();
			log.info(user_no + "**");
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.", Locale.KOREA);
		    LocalDate localDate = LocalDate.parse(vo.getAttend_date(), formatter);
			AttendanceVo attend = userAttendMapper.findAttendByUserNoDate(user_no, 
					localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
			log.info(attend.toString());
			AttendPolicyDto apdto = managerApprovalMapper.findAttendTimeByUserNo(user_no);
			LocalTime start = attend.getAttend_start() != null ? attend.getAttend_start().toLocalTime() : null;
			LocalTime end = attend.getAttend_end() != null ? attend.getAttend_end().toLocalTime() : null;
			// attendance_edit_approval 테이블 추가
			dto.setUser_no(user_no);
			dto.setAttend_no(attend.getAttend_no());
			dto.setAttendedit_title(vo.getAttendedit_title());
			dto.setAttendedit_kind(vo.getAttendedit_kind());
			dto.setAttendedit_date(attend.getAttend_date());
			dto.setAttendedit_start_time(vo.getAttendedit_start_time());
			dto.setAttendedit_end_time(vo.getAttendedit_end_time());
			dto.setAttendapp_user_no(vo.getAttendapp_user_no());
			dto.setAttendapp_status(vo.getAttendapp_status());
			dto.setAttendapp_reason(vo.getAttendapp_reason());
			dto.setAttendapp_date(localDate);
			dto.setAttendoriginal_start_time(start);
			dto.setAttendoriginal_end_time(end);
			dto.setAttendoriginal_status(attend.getAttend_status());
			managerModifyMapper.saveAttendModify(dto);
			// attendance 테이블 수정
			HalfLeaveDto hldto = new HalfLeaveDto(); // 반차 사용 여부
			hldto.setUser_no(user_no);
			hldto.setAttend_date(localDate);
			ApprovalAttendRequestDto appAttendDto  = new ApprovalAttendRequestDto();
			appAttendDto.setUser_no(user_no);
			appAttendDto.setAttend_no(attend.getAttend_no());
			appAttendDto.setAttend_date(localDate);
			// 수정 내용에 따라서 attend_start, attend_end 지정
			if(vo.getAttendedit_kind() == 3) { // 0. 출근만 수정
				appAttendDto.setAttend_start(vo.getAttendedit_start_time()); 
				appAttendDto.setAttend_end(end);
			} else if(vo.getAttendedit_kind() == 4) { // 1. 퇴근만 수정
				appAttendDto.setAttend_start(start);
				appAttendDto.setAttend_end(vo.getAttendedit_end_time());
			} else { // 2. 출근과 퇴근 수정				
				appAttendDto.setAttend_start(vo.getAttendedit_start_time()); 			
				appAttendDto.setAttend_end(vo.getAttendedit_end_time());
			}
			// 수정된 출퇴근 시간에 따라서 attend_total, attend_status 지정
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			if(managerApprovalMapper.findHalfLeaveByUserNo(hldto) == 1) { // 오전 반차
				map = managerApprovalService.attendConfirm(appAttendDto.getAttend_start(), apdto.getStandard_start_time(), 
						appAttendDto.getAttend_end(), apdto.getStandard_end_time().minusHours(5), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			} else if (managerApprovalMapper.findHalfLeaveByUserNo(hldto) == 2 ) { // 오후 반차
				map = managerApprovalService.attendConfirm(appAttendDto.getAttend_start(), apdto.getStandard_start_time().plusHours(5), 
						appAttendDto.getAttend_end(), apdto.getStandard_end_time(), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			} else {
				map = managerApprovalService.attendConfirm(appAttendDto.getAttend_start(), apdto.getStandard_start_time(), 
						appAttendDto.getAttend_end(), apdto.getStandard_end_time(), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			}
			appAttendDto.setAttend_total((LocalTime)map.get(1));
			appAttendDto.setAttend_status((String)map.get(2));
			log.info("****" + appAttendDto.toString());
			managerApprovalMapper.updateAttend(appAttendDto);
		}
	}
}
