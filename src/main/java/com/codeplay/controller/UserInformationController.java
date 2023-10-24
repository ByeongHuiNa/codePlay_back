package com.codeplay.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeplay.domain.UserInformationResponseVo;

@RestController
public class UserInformationController {
	
	@GetMapping("/user_information")
	public List<UserInformationResponseVo> get_user_information()
	{
		System.out.println("요청 들어옴");
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
		list.add(user);
		return list;
	}

}
