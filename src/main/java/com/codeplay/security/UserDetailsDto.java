package com.codeplay.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
@Data
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {

    //TODO:Delegate 패턴 찾아보기
    @Delegate
    private UserDto userDto;
    private Collection<? extends GrantedAuthority>authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getUser_password();
    }

    @Override
    public String getUsername() {
        return userDto.getUser_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
