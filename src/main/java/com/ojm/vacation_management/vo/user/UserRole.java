package com.ojm.vacation_management.vo.user;

import lombok.Getter;

@Getter
public enum UserRole {
    MEMBER(0, "회원"),
    ADMIN(1, "관리자");

    private final int code;
    private final String desc;

    UserRole(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
