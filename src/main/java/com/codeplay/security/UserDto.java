package com.codeplay.security;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Integer user_no;
    private String user_name;
    private String user_email;
    private String user_password;
    private boolean user_password_is_temp;

    @Builder
    public UserDto(Integer user_no, String user_name, String user_email, String user_password, boolean user_password_is_temp) {
        this.user_no = user_no;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_password_is_temp = user_password_is_temp;
    }
}
