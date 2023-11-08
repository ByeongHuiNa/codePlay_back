package com.codeplay.security;

import com.codeplay.domain.Access_PageVo;
import com.codeplay.mapper.userInformation.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserDto> login(UserDto userDto) {
        Optional<UserDto> login = userMapper.login(userDto);
        if (login != null) {
                List<Access_PageVo> list = userMapper.findPageByUserNo(login.get().getUser_no());
                if(list.size() == 0){
                    list = userMapper.findPageByUserNoNull(login.get().getUser_no());
                }
                List<Integer> integerList = new ArrayList<>();
                for (Access_PageVo l : list) {
                    integerList.add(l.getPage_no());
                }
                login.get().setPage_no(integerList);


        }
        return login;
    }
}
