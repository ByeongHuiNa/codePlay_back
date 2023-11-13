package com.codeplay.service.managerApproval;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeplay.domain.AttendanceVo;
import com.codeplay.domain.managerApproval.dto.AddAttendDto;
import com.codeplay.domain.managerApproval.dto.ApprovalAttendRequestDto;
import com.codeplay.domain.managerApproval.dto.AttendPolicyDto;
import com.codeplay.domain.managerApproval.dto.DeptLeaveRequestDto;
import com.codeplay.domain.managerApproval.dto.HalfLeaveDto;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendRequestVo;
import com.codeplay.domain.managerApproval.vo.ApprovalAttendResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalLeaveResponseVo;
import com.codeplay.domain.managerApproval.vo.ApprovalRequestVo;
import com.codeplay.domain.managerApproval.vo.DeptLeaveRequestVo;
import com.codeplay.mapper.managerApproval.ManagerApprovalMapper;
import com.codeplay.mapper.userLeave.UserLeaveMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManagerApprovalServiceImpl implements ManagerApprovalService {
	@Autowired
	ManagerApprovalMapper managerApprovalMapper;
	
	@Autowired
	UserLeaveMapper userLeaveMapper;

	@Override
	public List<ApprovalLeaveResponseVo> getLeaveApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findLeaveApprovalByUserNo(user_no);
	}

	@Override
	public List<ApprovalAttendResponseVo> getAttendApprovalByUserNo(int user_no) {
		return managerApprovalMapper.findAttendApprovalByUserNo(user_no);
	}

	@Transactional
	@Override // 근태담당자의 휴가 결재 처리 1차
	public void updateFirstLeaveApproval(ApprovalRequestVo vo) {
			if(vo.getLeaveapp_type() == 4) { // 휴가취소
				managerApprovalMapper.updateLeaveCancelApproval(vo);
				if(vo.getLeaveappln_status() == 0) { // 승인
					userLeaveMapper.deleteLeaveRequestByAppNo(vo.getLeaveapp_cancel_no());
					managerApprovalMapper.updateLeave(vo.getUser_no(), -vo.getLeaveapp_total());
				} 
			} else {				
				int result = managerApprovalMapper.updateFirstLeaveApproval(vo); // 휴가신청
				log.info("result : " + result);
			}
	}

	@Transactional
	@Override // 근태담당자의 휴가 결재 처리 2차
	public void updateSecondLeaveApproval(ApprovalRequestVo vo) {
		if(vo.getLeaveappln_status() == 0) { // 승인
			int user_no = vo.getUser_no();
			double leaveapp_total = vo.getLeaveapp_total();
			managerApprovalMapper.updateLeave(user_no, leaveapp_total);
			for(int i=0; i<vo.getLeaveapp_total(); i++) {
				AddAttendDto dto = new AddAttendDto();
				dto.setUser_no(vo.getUser_no());
				dto.setAttend_date(vo.getLeaveapp_start().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(i));
				dto.setStatus(vo.getLeaveapp_type() == 0 ? "휴가(연차)" : vo.getLeaveapp_type() == 3 ? "휴가(공가)"  :
						vo.getLeaveapp_type() == 2 ? "휴가(오전반차)": "휴가(오후반차)");
				dto.setAttend_total(vo.getLeaveapp_type() == 0 || vo.getLeaveapp_type() == 3 ? LocalTime.of(8, 0, 0) : null);
				managerApprovalMapper.saveLeaveToAttendance(dto);
			}
		}
		managerApprovalMapper.updateSecondLeaveApproval(vo);
	}

	@Transactional
	@Override // 근태담당자의 출퇴근 수정 결재 처리
	public void updateAttendApproval(ApprovalAttendRequestVo vo) {
		// 수정 요청자의 정규 출퇴근 시간
		AttendPolicyDto apdto = managerApprovalMapper.findAttendTimeByUserNo(vo.getUser_no());
		managerApprovalMapper.updateAttendApproval(vo); // 출퇴근 수정 결재 테이블 
		if(vo.getAttendapp_status() == 0) { // 결재 승인 처리인 경우 출퇴근 테이블 수정
			HalfLeaveDto hldto = new HalfLeaveDto();
			hldto.setUser_no(vo.getUser_no());
			hldto.setAttend_date(vo.getAttend_date());
			ApprovalAttendRequestDto dto  = new ApprovalAttendRequestDto();
			dto.setUser_no(vo.getUser_no());
			dto.setAttend_no(vo.getAttend_no());
			// 수정 내용에 따라서 attend_start, attend_end 지정
			if(vo.getAttendedit_kind() == 0) { // 0. 출근만 수정
				dto.setAttend_start(vo.getAttendedit_start_time()); 
				dto.setAttend_end(vo.getAttend_end());
			} else if(vo.getAttendedit_kind() == 1) { // 1. 퇴근만 수정
				dto.setAttend_start(vo.getAttend_start());
				dto.setAttend_end(vo.getAttendedit_end_time());
			} else { // 2. 출근과 퇴근 수정				
				dto.setAttend_start(vo.getAttendedit_start_time()); 			
				dto.setAttend_end(vo.getAttendedit_end_time());
			}
			// 수정된 출퇴근 시간에 따라서 attend_total, attend_status 지정
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			if(managerApprovalMapper.findHalfLeaveByUserNo(hldto) == 1) { // 오전 반차
				map = attendConfirm(dto.getAttend_start(), apdto.getStandard_start_time(), 
						dto.getAttend_end(), apdto.getStandard_end_time().minusHours(5), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			} else if (managerApprovalMapper.findHalfLeaveByUserNo(hldto) == 2 ) { // 오후 반차
				map = attendConfirm(dto.getAttend_start(), apdto.getStandard_start_time().plusHours(5), 
						dto.getAttend_end(), apdto.getStandard_end_time(), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			} else {
				map = attendConfirm(dto.getAttend_start(), apdto.getStandard_start_time(), 
						dto.getAttend_end(), apdto.getStandard_end_time(), managerApprovalMapper.findHalfLeaveByUserNo(hldto));
			}
			dto.setAttend_total((LocalTime)map.get(1));
			dto.setAttend_status((String)map.get(2));
			dto.setAttend_date(vo.getAttend_date());
			log.info(dto.toString());
			managerApprovalMapper.updateAttend(dto);
		}
	}

	@Override
	public HashMap<Integer, Object> attendConfirm(LocalTime start, LocalTime standard_start, LocalTime end,
			LocalTime standard_end, int half_leave) {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		if(start != null && end != null) {
			// 출근시간이 정책 시간보다 늦고 퇴근 시간은 정책 시간보다 빠르지 않다 (지각)
			if(standard_start.compareTo(start) == -1 && standard_end.compareTo(end) != 1) {
				int totalSecond = (standard_end.getHour() * 3600 + standard_end.getMinute() * 60 + standard_end.getSecond()) - 
						(start.getHour() * 3600 + start.getMinute() * 60 + start.getSecond());
				LocalTime totalTime = half_leave == 1 || half_leave == 2 ? 
						LocalTime.of(totalSecond/3600, totalSecond%3600/60, totalSecond%3600%60) : 
						LocalTime.of(totalSecond/3600 - 1, totalSecond%3600/60, totalSecond%3600%60);	
				map.put(1, totalTime);
				map.put(2, "지각");
				// 출근시간이 정책 시간보다 늦지 않고 퇴근 시간은 정책 시간보다 빠르다. (조퇴)
			} else if(standard_start.compareTo(start) != -1 && standard_end.compareTo(end) == 1) {
				int totalSecond = (end.getHour() * 3600 + end.getMinute() * 60 + end.getSecond()) - 
						(standard_start.getHour() * 3600 + standard_start.getMinute() * 60 + standard_start.getSecond());
				LocalTime totalTime = half_leave == 1 || half_leave == 2 ? 
						LocalTime.of(totalSecond/3600, totalSecond%3600/60, totalSecond%3600%60) : 
						LocalTime.of(totalSecond/3600 - 1, totalSecond%3600/60, totalSecond%3600%60);	
				map.put(1, totalTime);
				map.put(2, "조퇴");
				// 출근시간이 정책 시간보다 늦지 않고 퇴근 시간도 정책 시간보다 빠르지 않다 (정상)
			} else if(standard_start.compareTo(start) != -1 && standard_end.compareTo(end) != 1) {
				LocalTime totalTime = half_leave == 1 || half_leave == 2 ? LocalTime.of(4, 0, 0) : LocalTime.of(8, 0, 0);
				map.put(1, totalTime);
				map.put(2, "정상");
				// 출근시간이 정책 시간보다 늦고 퇴근 시간도 정책 시간보다 빠름 (지각, 조퇴 -> 결근으로 처리, 근무시간은 계산 OK)
			} else if(standard_start.compareTo(start) == -1 && standard_end.compareTo(end) == 1) { 
				int totalSecond = (end.getHour() * 3600 + end.getMinute() * 60 + end.getSecond()) - 
						(start.getHour() * 3600 + start.getMinute() * 60 + start.getSecond());
				LocalTime totalTime = half_leave == 1 || half_leave == 2 ? 
						LocalTime.of(totalSecond/3600, totalSecond%3600/60, totalSecond%3600%60) : 
						LocalTime.of(totalSecond/3600 - 1, totalSecond%3600/60, totalSecond%3600%60);	
				map.put(1, totalTime);
				map.put(2, "결근");
			} else { // 모두 아닌 경우 결근 처리
				map.put(1, LocalTime.of(0, 0, 0));
				map.put(2, "결근");
			}
		} else { // 출근시간 혹은 퇴근시간 중 하나라도 null 값일 때는 결근 (근무시간 계산 X)
			map.put(1, LocalTime.of(0, 0, 0));
			map.put(2, "결근");
		}
		return map;
	}

	@Override
	public HashMap<Integer, Object> getDeptLeaveByUserNo(DeptLeaveRequestVo vo) {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.", Locale.KOREA);
	    LocalDate start = LocalDate.parse(vo.getLeaveapp_start(), formatter);
	    LocalDate end = LocalDate.parse(vo.getLeaveapp_end(), formatter);
	    int period = Period.between(start, end).getDays()+1;
	    int count = 0;
	    LocalDate countDate = start;
	    for(int i=0; i<period; i++) {
	    	DeptLeaveRequestDto dto = new DeptLeaveRequestDto();
	    	dto.setUser_no(vo.getUser_no());
	    	dto.setLeaveapp_date(start.plusDays(i));
	    	int deptCnt = managerApprovalMapper.findDeptLeaveByUserNo(dto);
	    	if(count < deptCnt) {
	    		count = deptCnt;
	    		countDate = start.plusDays(i);
	    	}
	    }
	    map.put(0, countDate.toString());
	    map.put(1, count);
	    map.put(2,  managerApprovalMapper.findDeptUsersByUserNo(vo.getUser_no()));
		return map;
	}
}
