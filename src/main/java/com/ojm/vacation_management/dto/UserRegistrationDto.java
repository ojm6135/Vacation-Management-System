package com.ojm.vacation_management.dto;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.form.UserRegistrationForm;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRegistrationDto {
    private final String username;
    private final String password;
    private final String name;
    private final UserRole role;

    public static UserRegistrationDto fromForm(final UserRegistrationForm form) {
        return UserRegistrationDto.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .name(form.getName())
                .role(form.getRole())
                .build();
    }

    public static User toEntity(final UserRegistrationDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .role(dto.getRole())
                .status(UserStatus.ON_WORK)
                .build();
    }
}
