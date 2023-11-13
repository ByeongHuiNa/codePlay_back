package com.codeplay.controller;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.OvertimeVo;
import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.AccessPageListResponseVo;
import com.codeplay.domain.access.vo.CustomUserListResponseVo;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.access.vo.RoleAccessPageResponseVo;
import com.codeplay.domain.overTime.vo.RequestOvertimeVo;
import com.codeplay.service.access.AccessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "초과 근무관리", description = "초과근무 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class OverTimeController {
//	@Autowired
//	AccessService service;
//
	@Operation(summary = "초과 근무 신청", description = "사용자가 초가근무 신청할때 사용합니다.")
	@PostMapping("/over-time")
	public ResponseEntity<Object> PostOverTime(@RequestBody RequestOvertimeVo vo) {
		log.info("over-time호출됨, vo: {}", vo);
		return ResponseEntity.ok("post success");
	}

	@Operation(summary = "사용자별 초과근무 신청내역 조회할때 사용", description = "초과근무 페이지에서 목록탭을 클릭했을때 사용자가 신청한 초과근무 전체 조회합니다.")
	@Parameter(name = "user_no", description = "사용자를 식별하는 사용자번호")
	@GetMapping("/over-time")
	public List<OvertimeVo> getOverTimeList(@RequestParam int user_no) {
//		log.info("access-page-list에 호출함. user_no: " + user_no);
//		List<CustomAccessPageDto> roleAccessPageList = service.getAccessPageList(user_no);
//		log.info("서비스로부터 받아온 데이터 roleAccessPageList: " + roleAccessPageList);
//		// ResponseVo 객체로 포장
//		AccessPageListResponseVo vo = new AccessPageListResponseVo();
//		vo.setAccess_page_list(roleAccessPageList);
		List<OvertimeVo> list = new ArrayList();
//		list.add(vo);
		return list;
	}

	@Operation(summary = "초과 근무 신청취소", description = "사용자가 초가근무 신청한것을 결재전 취소할때 사용합니다.")
	@PutMapping("/over-time")
	public ResponseEntity<Object> PutOverTime(@RequestBody RequestOvertimeVo vo) {
		log.info("put over-time호출됨, vo: {}", vo);
		return ResponseEntity.ok("post success");
	}
	@Operation(summary = "근태담당자별 초과근무 신청내역 조회할때 사용", description = "근태담당 페이지에서 초과근무목록탭을 클릭했을때 근태담당자가 결재할 초과근무 전체 조회합니다.")
	@Parameter(name = "user_no", description = "사용자를 식별하는 사용자번호(근태담당자)")
	@GetMapping("/over-time-manager")
	public List<OvertimeVo> getOverTimeManagerList(@RequestParam int user_no) {
		//TODO:헤더의 토큰으로 권한 확인해야함.
//		log.info("access-page-list에 호출함. user_no: " + user_no);
//		List<CustomAccessPageDto> roleAccessPageList = service.getAccessPageList(user_no);
//		log.info("서비스로부터 받아온 데이터 roleAccessPageList: " + roleAccessPageList);
//		// ResponseVo 객체로 포장
//		AccessPageListResponseVo vo = new AccessPageListResponseVo();
//		vo.setAccess_page_list(roleAccessPageList);
		List<OvertimeVo> list = new ArrayList();
//		list.add(vo);
		return list;
	}
	@Operation(summary = "초과 근무 결재", description = "근태담당자가 초가근무 신청한것을 결재할때 사용합니다.")
	@PutMapping("/over-time-manager")
	public ResponseEntity<Object> PutOverTimeManager(@RequestBody RequestOvertimeVo vo) {
		log.info("put over-time-manager호출됨, vo: {}", vo);
		return ResponseEntity.ok("success");
	}

}
