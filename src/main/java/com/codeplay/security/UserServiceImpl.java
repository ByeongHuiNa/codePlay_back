package com.codeplay.security;

import com.codeplay.mapper.userInformation.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserDto> login(UserDto userDto) {
        return userMapper.login(userDto);
    }
}
