package com.ojm.vacation_management.vo.user;

import lombok.Getter;

@Getter
public enum UserStatus {
    ON_WORK(0),
    ON_VACATION(1);

    private final int code;

    UserStatus(int code) {
        this.code = code;
    }
}
