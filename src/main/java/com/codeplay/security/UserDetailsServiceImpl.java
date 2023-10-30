package com.codeplay.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        UserDto userDto = UserDto
                .builder()
                .user_email(user_email)
                .build();

        // 사용자 정보가 존재하지 않는 경우
        if (user_email == null || user_email.equals("")) {
            return userService.login(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUser_email()))))
                    .orElseThrow(() -> new AuthenticationServiceException(user_email));
        }
        // 비밀번호가 맞지 않는 경우
        else {
            return userService.login(userDto)
                    .map(u -> new UserDetailsDto(u, Collections.singleton(new SimpleGrantedAuthority(u.getUser_email()))))
                    .orElseThrow(() -> new BadCredentialsException(user_email));
        }
    }
}
