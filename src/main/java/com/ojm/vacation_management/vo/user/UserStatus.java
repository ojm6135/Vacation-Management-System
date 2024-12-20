package com.ojm.vacation_management.vo.user;

import lombok.Getter;

@Getter
public enum UserStatus {
    ON_WORK(0, "근무"),
    ON_VACATION(1, "휴가");

    private final int code;
    private final String desc;

    UserStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
