package com.ojm.vacation_management.vo.user;

import com.ojm.vacation_management.domain.User;
import lombok.Getter;

@Getter
public enum UserRole {
    MEMBER(0),
    APPROVER(1);

    private final int code;

    UserRole(int code) {
        this.code = code;
    }
}
