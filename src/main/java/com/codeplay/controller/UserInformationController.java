package com.codeplay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.codeplay.domain.userInformation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.userInformation.dto.UserInformationDto;
import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
import com.codeplay.domain.userInformation.dto.UserQueryDto;
import com.codeplay.service.userInformation.UserInformationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 회원정보 관리 기능", description = "사용자 회원정보 관리에 필요한 API")
@RestController
@Slf4j
public class UserInformationController {
	@Autowired
	UserInformationService service;
	//일자 format


	@Operation(summary = "user 사용자 정보 페이지 조회", description = "회원정보조회 페이지에서 user 의 사용자 정보를 조회할때 사용합니다.")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-information")
	public List<UserInformationResponseVo> getUserInformation(@RequestParam int user_no) {
		log.info("user-information에 호출함. user_no: "+user_no);
		List<UserInformationResponseVo> list = new ArrayList();
		UserInformationDto user = service.getUserData(user_no);
		log.info("서비스로부터 받아온 데이터 user: "+user);
		//ResponseVo 객체로 포장
		UserInformationResponseVo vo = new UserInformationResponseVo();
		vo.setDept_name(user.getDept().getDept_name());
		vo.setUser_address(user.getUser().getUser_address());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		vo.setUser_birth_date(format.format(user.getUser().getUser_birth_date()));
		vo.setUser_email(user.getUser().getUser_email());
		vo.setUser_name(user.getUser().getUser_name());
		vo.setUser_no(user.getUser().getUser_no());
		vo.setUser_phone(user.getUser().getUser_phone());
		vo.setUser_position(user.getUser().getUser_position());
		vo.setUser_profile(user.getUser().getUser_profile());
		list.add(vo);
		return list;
	}
	
	@Operation(summary = "user 사용자 정보 변경", description = "회원정보조회 페이지에서 user 의 사용자 정보의 변경사항을 저장할때 사용합니다.")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@PatchMapping("/user-information")
	public void patchUserInformation(@RequestParam int user_no, @RequestBody UserInformationRequestVo user) {
		log.info("user-information에 호출함. user_no: {} request_body {}",user_no, user);
		UserInformationPatchDto userPatch = new UserInformationPatchDto();
		userPatch.setUser_no(user_no);
		userPatch.setUser_address(user.getUser_address());
		userPatch.setUser_phone(user.getUser_phone());
		userPatch.setUser_profile(user.getUser_profile());
		log.info("서비스로 전달한 데이터 userPatch: "+userPatch);
		service.patchUser(userPatch);
	}
	
	@Operation(summary = "user 사용자 정보 테이블 조회", description = "사용자 조회페이지의 user 의 사용자 정보를 테이블로 조회할때 사용합니다. 이름으로검색가능, pagenation가능")
	@Parameter(name = "user_name", description = "유저 개인을 검색하기위한 유저이름")
	@Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
	@Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
	@GetMapping("/user-query")
	public List<UserQueryResponseVo> getUserQuery(@RequestParam String user_name, @RequestParam Integer page, @RequestParam Integer limit) {
		log.info("user-query에 호출함. user_name: {} page: {} limit: {}", user_name, page-1, limit);
		CriteriaVo cri = new CriteriaVo(user_name, page-1, limit);
		List<UserQueryResponseVo> list = new ArrayList();
		List<UserQueryDto> users = service.getUserByUsername(cri);
		log.info("서비스로부터 받아온 데이터 user: "+users);
		for(UserQueryDto userQuery : users) {
			UserQueryResponseVo user = new UserQueryResponseVo();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			user.setUser_birth_date(format.format(userQuery.getUser_birth_date()));
			user.setUser_profile(userQuery.getUser_profile());
			user.setDept_name(userQuery.getDept_name());
			user.setUser_name(userQuery.getUser_name());
			user.setUser_no(userQuery.getUser_no());
			user.setUser_position(userQuery.getUser_position());
			list.add(user);
		}
		return list;
	}
	@Operation(summary = "조직도 조회", description = "사용자 조회페이지의 조직도를 전체 조회할때 사용합니다. 1단으로 구성됨.")
	@GetMapping("/user-query-list")
	public List<UserQueryListResponseVo> getUserQueryList() {
		log.info("user-query-list에 호출함.");
		List<UserQueryListResponseVo> list = new ArrayList();
		List<UserQueryDto> users = service.getAllUser();
		log.info("서비스로부터 받아온 데이터 user: "+users);
		for(UserQueryDto userQuery : users) {
			UserQueryListResponseVo user = new UserQueryListResponseVo();
			user.setDept_name(userQuery.getDept_name());
			user.setUser_name(userQuery.getUser_name());
			user.setUser_no(userQuery.getUser_no());
			user.setUser_position(userQuery.getUser_position());
			list.add(user);
		}
		return list;
	}
	@Operation(summary = "부서 전체 조회", description = "부서를 전체 조회할때 사용합니다.")
	@GetMapping("/dept-list")
	public List<DeptListResponseVo> getDeptList() {
		log.info("dept-list에 호출함.");
		List<DeptListResponseVo> deptList = service.getAllDept();
		return deptList;
	}

}
