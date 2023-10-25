package com.codeplay.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.userInformation.vo.UserInformationResponseVo;
import com.codeplay.domain.userInformation.vo.UserQueryListResponseVo;
import com.codeplay.domain.userInformation.vo.UserQueryResponseVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "사용자 회원정보 관리 기능", description = "사용자 회원정보 관리에 필요한 API")
@RestController
@Slf4j
public class UserInformationController {

	@Operation(summary = "user 사용자 정보 페이지 조회", description = "회원정보조회 페이지에서 user 의 사용자 정보를 조회할때 사용합니다.")
	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
	@GetMapping("/user-information")
	public List<UserInformationResponseVo> get_user_information(@RequestParam String user_no) {
		log.info("user-information에 호출함. user_no: "+user_no);
		List<UserInformationResponseVo> list = new ArrayList();
		UserInformationResponseVo user = new UserInformationResponseVo();
		user.setDept_name("개발팀");
		user.setUser_address("서울특별시 종로구 창경궁로 254-123");
		user.setUser_birth_date("1960-01-24");
		user.setUser_email("dylan@douzon.com");
		user.setUser_name("홍길동");
		user.setUser_no(0);
		user.setUser_phone("010-5698-0001");
		user.setUser_position("연구원");
		user.setUser_profile("https://picsum.photos/200");
		log.info("서비스로부터 받아온 데이터 user: "+user);
		list.add(user);
		return list;
	}
	
	@Operation(summary = "user 사용자 정보 테이블 조회", description = "사용자 조회페이지의 user 의 사용자 정보를 테이블로 조회할때 사용합니다. 이름으로검색가능, pagenation가능")
	@Parameter(name = "user_name", description = "유저 개인을 검색하기위한 유저이름")
	@Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
	@Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
	@GetMapping("/user-query")
	public List<UserQueryResponseVo> get_user_query(@RequestParam String user_name, @RequestParam Integer page, @RequestParam Integer limit) {
		log.info("user-query에 호출함. user_name: {} page: {} limit: {}", user_name, page, limit);
		List<UserQueryResponseVo> list = new ArrayList();
		UserQueryResponseVo user = new UserQueryResponseVo();
		user.setDept_name("개발팀");
		user.setUser_birth_date("1960-01-24");
		user.setUser_name("홍길동");
		user.setUser_no(0);
		user.setUser_position("연구원");
		user.setUser_profile("https://picsum.photos/200");
		log.info("서비스로부터 받아온 데이터 user: "+user);
		list.add(user);
		return list;
	}
	@Operation(summary = "조직도 조회", description = "사용자 조회페이지의 조직도를 전체 조회할때 사용합니다. 1단으로 구성됨.")
	@GetMapping("/user-query-list")
	public List<UserQueryListResponseVo> get_user_query_list() {
		log.info("user-query-list에 호출함.");
		List<UserQueryListResponseVo> list = new ArrayList();
		UserQueryListResponseVo user = new UserQueryListResponseVo();
		user.setDept_name("개발팀");
		user.setUser_name("홍길동");
		user.setUser_no(0);
		user.setUser_position("연구원");
		log.info("서비스로부터 받아온 데이터 user: "+user);
		list.add(user);
		return list;
	}

}
