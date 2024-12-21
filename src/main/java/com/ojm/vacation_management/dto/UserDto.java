package com.ojm.vacation_management.dto;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private final int id;
    private final String username;
    private final String name;
    private final UserRole role;
    private final UserStatus status;

    public static UserDto fromEntity(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public static User toEntity(final UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .name(userDto.getName())
                .role(userDto.getRole())
                .status(userDto.getStatus())
                .build();
    }


}
