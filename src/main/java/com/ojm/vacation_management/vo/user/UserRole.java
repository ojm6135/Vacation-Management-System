package com.ojm.vacation_management.vo.user;

import lombok.Getter;

@Getter
public enum UserRole {
    MEMBER(0, "ROLE_MEMBER", "회원"),
    ADMIN(1, "ROLE_ADMIN","관리자");

    private final int code;
    private final String name;
    private final String desc;

    UserRole(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
