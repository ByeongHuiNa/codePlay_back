package com.codeplay.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.codeplay.domain.access.dto.RoleAccessPageDto;
import com.codeplay.domain.access.vo.RoleAccessPageResponseVo;
import com.codeplay.service.access.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;
import com.codeplay.domain.policy.vo.PolicyCountResponseVo;
import com.codeplay.domain.policy.vo.UserPolicyDetailResponseVo;
import com.codeplay.domain.policy.vo.UserPolicyListResponseVo;

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

//	@Operation(summary = "사용자별 접근 페이지 조회할때 사용", description = "접근 관리 페이지에서 사용자별 탭에서 사용자가 선택되었을때 전체 조회합니다.")
//	@GetMapping("/access-page-list")
//	public List<UserPolicyListResponseVo> get_policy_list(@RequestParam int policy_no, @RequestParam int page,
//			@RequestParam int limit) {
//		log.info("user-policy-list에 호출함. policy_no: " + policy_no);
//		List<UserPolicyListResponseVo> list = new ArrayList();
//		CriteriaVo cri = new CriteriaVo(null, page - 1, limit);
//		PolicyQueryDto policyQuery = new PolicyQueryDto(cri, policy_no);
//		List<PolicyUserDto> users = service.getUserByPolicyNo(policyQuery);
//		log.info("서비스로부터 받아온 데이터 user: " + users);
//		// ResponseVo 객체로 포장
//		for (PolicyUserDto user : users) {
//			UserPolicyListResponseVo vo = new UserPolicyListResponseVo();
//			vo.setUser_no(user.getUser_no());
//			vo.setUser_profile(user.getUser_profile());
//			vo.setPolicy_no(user.getPolicy_no());
//			vo.setDept_name(user.getDept_name());
//			vo.setUser_name(user.getUser_name());
//			vo.setUser_position(user.getUser_position());
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			vo.setPolicy_designated_date(dateFormat.format(user.getPolicy_designated_date()));
//			list.add(vo);
//		}
//		return list;
//	}

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
//
//	@Operation(summary = "출 / 퇴근 정책의 사용자수 와 종류 조회", description = "정책 관리 페이지에서 정책별탭 컴포넌트 생성시 필요한 데이터를 조회할때 사용합니다")
//	@GetMapping("/policy-count")
//	public List<PolicyCountResponseVo> get_policy_count() {
//		log.info("policy-count에 호출함");
//		List<PolicyCountResponseVo> list = new ArrayList();
//		List<PolicyCountDto> policyCount = service.getPolicyCount();
//		log.info("서비스로부터 받아온 데이터 user: " + policyCount);
//		for (PolicyCountDto count : policyCount) {
//			PolicyCountResponseVo vo = new PolicyCountResponseVo();
//			vo.setCount(count.getCount());
//			vo.setPolicy_name(count.getPolicy_name());
//			vo.setPolicy_no(count.getPolicy_no());
//			list.add(vo);
//		}
//		return list;
//	}

}
