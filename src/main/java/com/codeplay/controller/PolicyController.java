package com.codeplay.controller;

import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.policy.dto.PolicyCountDto;
import com.codeplay.domain.policy.dto.PolicyQueryDto;
import com.codeplay.domain.policy.dto.PolicyUserDetailDto;
import com.codeplay.domain.policy.dto.PolicyUserDto;
import com.codeplay.domain.policy.vo.PolicyCountResponseVo;
import com.codeplay.domain.policy.vo.UserPolicyDetailRequestVo;
import com.codeplay.domain.policy.vo.UserPolicyDetailResponseVo;
import com.codeplay.domain.policy.vo.UserPolicyListResponseVo;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.policy.PolicyService;
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

@Tag(name = "출 / 퇴근 정책 관리 기능", description = "정책 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class PolicyController {
    @Autowired
    PolicyService service;

    @Operation(summary = "정책별 사용자 리스트 조회할때 사용", description = "정책 관리 페이지에서 policy 별 user 의 사용자 정보를 테이블 형태로 조회할때 사용합니다.")
    @Parameter(name = "policy_no", description = "출퇴근 정책을 식별하기위한 정책번호")
    @Parameter(name = "user_name", description = "유저 개인을 검색하기위한 유저이름")
    @Parameter(name = "page", description = "pagenation에서 보여줄 현재 page number")
    @Parameter(name = "limit", description = "pagenation에서 보여줄 최대 갯수")
    @GetMapping("/user-policy-list")
    public ResponseEntity<Object> getPolicyList(@RequestParam(required = false) String user_name, @RequestParam int policy_no, @RequestParam int page,
                                                @RequestParam int limit, @RequestHeader("Authorization") String token) {
        log.info("user-policy-list에 호출함. user_name: {}, policy_no: {}, page: {}, limit : {}", user_name, policy_no, page, limit);
        if (TokenUtils.getPageListFromToken(token.substring(6)).contains(13)) {
            log.info("13번 페이지 권한(정책 관리페이지)이 있습니다.");
        } else
                    return ResponseEntity.status(403).build();
        List<UserPolicyListResponseVo> list = new ArrayList();
        CriteriaVo cri = new CriteriaVo(user_name, page - 1, limit);
        PolicyQueryDto policyQuery = new PolicyQueryDto(cri, policy_no);
        List<PolicyUserDto> users = service.getUserByPolicyNo(policyQuery);
        Integer totalCount = service.getTotal(policyQuery);
        log.info("서비스로부터 받아온 데이터 user: " + users);
        // ResponseVo 객체로 포장
        for (PolicyUserDto user : users) {
            UserPolicyListResponseVo vo = new UserPolicyListResponseVo();
            vo.setUser_no(user.getUser_no());
            vo.setUser_profile(user.getUser_profile());
            vo.setPolicy_no(user.getPolicy_no());
            vo.setDept_name(user.getDept_name());
            vo.setUser_name(user.getUser_name());
            vo.setUser_position(user.getUser_position());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            vo.setPolicy_designated_date(dateFormat.format(user.getPolicy_designated_date()));
            list.add(vo);
        }
        return ResponseEntity.ok().header("X-Total-Count", totalCount.toString()).body(list);
    }
    //TODO:이름으로 검색했을때 나오는 데이터 조회

    @Operation(summary = "한 사용자 정책 상세 조회", description = "정책 관리 페이지에서 정책별 user 테이블의 정책 변경 버튼을 눌렀을때 나오는 정책상세화면 데이터 조회할때 사용합니다.")
    @Parameter(name = "user_no", description = "유저 개인을 식별하기위한 유저번호")
    @GetMapping("/user-policy-detail")
    public ResponseEntity<Object> getPolicyDetail(@RequestParam int user_no, @RequestHeader("Authorization") String token) {
        log.info("user-policy-detail에 호출함. user_no: {} ", user_no);
        if (!(TokenUtils.getUserNoFromToken(token.substring(6)).equals(user_no + ""))) {
            if (TokenUtils.getPageListFromToken(token.substring(6)).contains(13)) {
                log.info("13번 페이지 권한(정책 관리페이지)이 있습니다.");
            } else
                return ResponseEntity.status(403).build();
        }
        PolicyUserDetailDto userDetail = service.getPolicyUserByUserNo(user_no);
        log.info("서비스로 호출한 데이터 userDetail: {}", userDetail);
        // ResponseVo 객체로 포장
        UserPolicyDetailResponseVo vo = new UserPolicyDetailResponseVo();
        vo.setUser_no(userDetail.getUser_no());
        vo.setUser_profile(userDetail.getUser_profile());
        vo.setPolicy_no(userDetail.getPolicy_no());
        vo.setDept_name(userDetail.getDept_name());
        vo.setUser_name(userDetail.getUser_name());
        vo.setUser_position(userDetail.getUser_position());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        vo.setStandard_start_time("0000T" + format.format(userDetail.getStandard_start_time()));
        vo.setStandard_end_time("0000T" + format.format(userDetail.getStandard_end_time()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        vo.setPolicy_designated_date(dateFormat.format(userDetail.getPolicy_designated_date()));
        List<UserPolicyDetailResponseVo> list = new ArrayList();
        list.add(vo);
        return ResponseEntity.ok(list);
    }

    //TODO : 한사용자가아니라 부서별로 변경한다면?
    @Operation(summary = "한 사용자 정책 변경", description = "정책상세화면의 데이터가 변경되고 저장할때 사용합니다.")
    @PostMapping("/user-policy-detail")
    public void PostUserPolicyDetail(@RequestBody UserPolicyDetailRequestVo policyDetail) {
        log.info("user-policy-detail에 호출함. userPolicyDetailRequestVo: {} ", policyDetail);
        PolicyUserDetailDto request = new PolicyUserDetailDto();
        request.setUser_no(policyDetail.getUser_no());
        request.setPolicy_no(policyDetail.getPolicy_no());
        service.save(request);
    }


    @Operation(summary = "출 / 퇴근 정책의 사용자수 와 종류 조회", description = "정책 관리 페이지에서 정책별탭 컴포넌트 생성시 필요한 데이터를 조회할때 사용합니다")
    @GetMapping("/policy-count")
    public ResponseEntity<Object> getPolicyCount(@RequestHeader("Authorization") String token) {
        log.info("policy-count에 호출함");
        if (TokenUtils.getPageListFromToken(token.substring(6)).contains(13)) {
            log.info("13번 페이지 권한(정책 관리페이지)이 있습니다.");
        } else
            return ResponseEntity.status(403).build();
        List<PolicyCountResponseVo> list = new ArrayList();
        List<PolicyCountDto> policyCount = service.getPolicyCount();
        log.info("서비스로부터 받아온 데이터 user: " + policyCount);
        for (PolicyCountDto count : policyCount) {
            PolicyCountResponseVo vo = new PolicyCountResponseVo();
            vo.setCount(count.getCount());
            vo.setPolicy_name(count.getPolicy_name());
            vo.setPolicy_no(count.getPolicy_no());
            list.add(vo);
        }
        return ResponseEntity.ok(list);
    }

}
