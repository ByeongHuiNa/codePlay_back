package com.codeplay.security;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> login(UserDto userDto);
}
