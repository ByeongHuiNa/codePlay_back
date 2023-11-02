package com.codeplay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.codeplay.domain.access.dto.CustomAccessPageDto;
import com.codeplay.domain.access.dto.CustomUserListDto;
import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.AccessPageListResponseVo;
import com.codeplay.domain.access.vo.CustomUserListResponseVo;
import com.codeplay.domain.access.vo.RoleAccessCountResponseVo;
import com.codeplay.domain.access.vo.RoleAccessPageResponseVo;
import com.codeplay.service.access.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.CriteriaVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "접근 관리 기능", description = "접근 관리에 필요한 API")
@RestController
@Slf4j
public class AccessController {
	@Autowired
	AccessService service;

	@Operation(summary = "개인 맞춤 접근 이용자 조회할때 사용", description = "접근 관리 페이지에서 개인 맞춤을 사용하고 있는 사용자를 조회합니다.")
	@Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
	@Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
	@GetMapping("/custom-user-list")
	public List<CustomUserListResponseVo> getCustomUserList(@RequestParam int page,
															@RequestParam int limit) {
		log.info("custom-user-list에 호출함.");
		List<CustomUserListResponseVo> list = new ArrayList();
		CriteriaVo cri = new CriteriaVo(null, page - 1, limit);
		List<CustomUserListDto> users = service.getCustomUserList(cri);
		log.info("서비스로부터 받아온 데이터 user: " + users);
		// ResponseVo 객체로 포장
		for (CustomUserListDto user : users) {
			CustomUserListResponseVo vo = new CustomUserListResponseVo();
			vo.setUser_no(user.getUser_no());
			vo.setUser_profile(user.getUser_profile());
			vo.setDept_name(user.getDept_name());
			vo.setUser_name(user.getUser_name());
			vo.setUser_position(user.getUser_position());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			vo.setRole_designated_date(dateFormat.format(user.getRole_designated_date()));
			list.add(vo);
		}
		return list;
	}

	@Operation(summary = "사용자별 접근 페이지 조회할때 사용", description = "접근 관리 페이지에서 사용자별 탭에서 사용자가 선택되었을때 사용자의 페이지를 전체 조회합니다.")
	@Parameter(name = "user_no", description = "사용자를 식별하는 사용자번호")
	@GetMapping("/access-page-list")
	public List<AccessPageListResponseVo> getAccessPageList(@RequestParam int user_no) {
		log.info("access-page-list에 호출함. user_no: " + user_no);
		List<CustomAccessPageDto> roleAccessPageList = service.getAccessPageList(user_no);
		log.info("서비스로부터 받아온 데이터 roleAccessPageList: " + roleAccessPageList);
		// ResponseVo 객체로 포장
		AccessPageListResponseVo vo = new AccessPageListResponseVo();
		vo.setAccess_page_list(roleAccessPageList);
		List<AccessPageListResponseVo> list = new ArrayList();
		list.add(vo);
		return list;
	}

	@Operation(summary = "권한별 접근페이지 조회", description = "접근 관리 페이지에서 권한별탭을 눌렀을떄(초기화면) 상세 탭 컴포넌트 데이터 조회할때 사용.")
	@Parameter(name = "role_level", description = "권한을 식별하는 권한수준")
	@GetMapping("/role-access-page")
	public List<RoleAccessPageResponseVo> getRoleAccessPage(@RequestParam int role_level) {
		log.info("role-access-page에 호출함. role_level: {} ", role_level);
		List<RoleAccessPageDto> rolePageList = service.getRoleAccessByRoleLevel(role_level);
		log.info("서비스로 호출한 데이터 rolePageList: {}", rolePageList);
		// ResponseVo 객체로 포장
		RoleAccessPageResponseVo vo = new RoleAccessPageResponseVo();
		vo.setAccess_page_list(rolePageList);
		List<RoleAccessPageResponseVo> list = new ArrayList();
		list.add(vo);
		return list;
	}

	@Operation(summary = "맞춤 접근 사용자수 조회", description = "접근 관리 페이지에서 탭 컴포넌트 생성시 필요한 데이터를 조회할때 사용합니다")
	@GetMapping("/custom-access-count")
	public List<RoleAccessCountResponseVo> getCustomAccessCount() {
		log.info("custom-access-count에 호출함");
		List<RoleAccessCountResponseVo> list = new ArrayList();
		RoleAccessCountResponseVo roleAccessCount = service.getCustomAccessCount();
		log.info("서비스로부터 받아온 데이터 user: " + roleAccessCount);
		list.add(roleAccessCount);
		return list;
	}

}
