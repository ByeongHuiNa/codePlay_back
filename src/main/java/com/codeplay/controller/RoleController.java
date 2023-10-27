//package com.codeplay.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.codeplay.domain.CriteriaVo;
//import com.codeplay.domain.UserVo;
//import com.codeplay.domain.policy.dto.PolicyCountDto;
//import com.codeplay.domain.policy.dto.PolicyQueryDto;
//import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
//import com.codeplay.domain.policy.dto.PolicyUserDto;
//import com.codeplay.domain.policy.vo.PolicyCountResponseVo;
//import com.codeplay.domain.policy.vo.UserPolicyDetailRequestVo;
//import com.codeplay.domain.policy.vo.UserPolicyDetailResponseVo;
//import com.codeplay.domain.policy.vo.UserPolicyListResponseVo;
//import com.codeplay.domain.role.dto.RoleCountDto;
//import com.codeplay.domain.role.dto.RoleQueryDto;
//import com.codeplay.domain.role.dto.RoleQueryListDto;
//import com.codeplay.domain.role.vo.RoleCountResponseVo;
//import com.codeplay.domain.role.vo.RoleQueryListResponseVo;
//import com.codeplay.domain.role.vo.RoleQueryUserDetailResponseVo;
//import com.codeplay.domain.userInformation.dto.UserInformationDto;
//import com.codeplay.domain.userInformation.dto.UserInformationPatchDto;
//import com.codeplay.domain.userInformation.dto.UserQueryDto;
//import com.codeplay.domain.userInformation.vo.UserInformationRequestVo;
//import com.codeplay.domain.userInformation.vo.UserInformationResponseVo;
//import com.codeplay.domain.userInformation.vo.UserQueryListResponseVo;
//import com.codeplay.domain.userInformation.vo.UserQueryResponseVo;
//import com.codeplay.mapper.userInformation.UserMapper;
//import com.codeplay.service.policy.PolicyService;
//import com.codeplay.service.userInformation.UserInformationService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.log4j.Log4j;
//import lombok.extern.slf4j.Slf4j;
//
//@Tag(name = "권한 관리 기능", description = "권한 관리에 필요한 API")
//@RestController
//@Slf4j
//public class RoleController {
//	@Autowired
//	RoleService service;
//
//	@Operation(summary = "권한별 사용자 리스트 조회할때 사용", description = "권한관리 페이지에서 권한별 user 의 사용자 정보를 테이블 형태로 조회할때 사용합니다.")
//	@Parameter(name = "role_level", description = "권한을 식별하기위한 권한수준")
//	@Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
//	@Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
//	@GetMapping("/role-query-list")
//	public List<RoleQueryListResponseVo> get_policy_list(@RequestParam int role_level, @RequestParam int page,
//			@RequestParam int limit) {
//		log.info("role-query-list에 호출함. policy_no: " + role_level);
//		List<RoleQueryListResponseVo> list = new ArrayList();
//		CriteriaVo cri = new CriteriaVo(null, page - 1, limit);
//		RoleQueryDto roleQuery = new RoleQueryDto(cri, role_level);
//		List<RoleQueryListDto> users = service.getUserByPolicyNo(roleQuery);
//		log.info("서비스로부터 받아온 데이터 user: " + users);
//		// ResponseVo 객체로 포장
//		for (RoleQueryListDto user : users) {
//			RoleQueryListResponseVo vo = new RoleQueryListResponseVo();
//			vo.setUser_no(user.getUser_no());
//			vo.setUser_profile(user.getUser_profile());
//			vo.setRole_level(user.getRole_level());
//			vo.setDept_name(user.getDept_name());
//			vo.setRole_name(user.getRole_name());
//			vo.setUser_name(user.getUser_name());
//			vo.setUser_position(user.getUser_position());
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			vo.setRole_designated_date(dateFormat.format(user.getRole_designated_date()));
//			list.add(vo);
//		}
//		return list;
//	}
//
//	@Operation(summary = "한 사용자 권한 상세 조회", description = "권한 관리 페이지에서 user 테이블의 권한 변경 버튼을 눌렀을때 나오는 권한상세화면 데이터 조회할때 사용합니다.")
//	@Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
//	@GetMapping("/role-query-user-detail")
//	public List<RoleQueryUserDetailResponseVo> getRoleUserDetail(@RequestParam int user_no) {
//		log.info("role-query-user-detail에 호출함. user_no: {} ", user_no);
//		PolicyUserDetailDto userDetail = service.getPolicyUserByUserNo(user_no);
//		log.info("서비스로 호출한 데이터 userDetail: {}", userDetail);
//		// ResponseVo 객체로 포장
//		UserPolicyDetailResponseVo vo = new UserPolicyDetailResponseVo();
//		vo.setUser_no(userDetail.getUser_no());
//		vo.setUser_profile(userDetail.getUser_profile());
//		vo.setPolicy_no(userDetail.getPolicy_no());
//		vo.setDept_name(userDetail.getDept_name());
//		vo.setUser_name(userDetail.getUser_name());
//		vo.setUser_position(userDetail.getUser_position());
//		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//		vo.setStandard_start_time("0000T"+format.format(userDetail.getStandard_start_time()));
//		vo.setStandard_end_time("0000T"+format.format(userDetail.getStandard_end_time()));
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		vo.setPolicy_designated_date(dateFormat.format(userDetail.getPolicy_designated_date()));
//		List<UserPolicyDetailResponseVo> list = new ArrayList();
//		list.add(vo);
//		return list;
//	}
//
//	@Operation(summary = "한 사용자 권한 변경", description = "권한상세화면의 데이터가 변경되고 저장할때 사용합니다.")
//	@PostMapping("/role-save")
//	public void saveRole(@RequestBody UserPolicyDetailRequestVo policyDetail) {
//		log.info("/role-save에 호출함. user_no: {}, userPolicyDetailRequestVo: {} ", user_no, policyDetail);
//		PolicyUserDetailDto request = new PolicyUserDetailDto();
//		request.setUser_no(policyDetail.getUser_no());
//		request.setPolicy_no(policyDetail.getPolicy_no());
//		request.setStandard_start_time(policyDetail.getStandard_start_time());
//		request.setStandard_end_time(policyDetail.getStandard_end_time());
//		request.setPolicy_designated_date(policyDetail.getPolicy_designated_date());
//		service.save(request);
//		
//	}
//	
//	
//
//	@Operation(summary = "권한의 사용자수 와 종류 조회", description = "권한 관리 페이지에서 탭 컴포넌트 생성시 필요한 데이터를 조회할때 사용합니다")
//	@GetMapping("/role-count")
//	public List<RoleCountResponseVo> getRoleCount() {
//		log.info("role-count에 호출함");
//		List<RoleCountResponseVo> list = new ArrayList();
//		List<RoleCountDto> roleCount = service.getPolicyCount();
//		log.info("서비스로부터 받아온 데이터 roleCount: " + roleCount);
//		for (RoleCountDto count : roleCount) {
//			RoleCountResponseVo vo = new RoleCountResponseVo();
//			vo.setCount(count.getCount());
//			vo.setRole_name(count.getRole_name());
//			vo.setRole_level(count.getRole_level());
//			list.add(vo);
//		}
//		return list;
//	}
//
//}
