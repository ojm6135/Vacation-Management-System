package com.ojm.vacation_management.vo.vacation;

import lombok.Getter;

@Getter
public enum VacationType {
    REGULAR_VACATION(0, "정기휴가"),
    SPECIAL_VACATION(1, "특별휴가"),
    EMERGENCY_LEAVE(2, "긴급휴가"),
    SICK_LEAVE(3, "병가");

    private final int code;
    private final String desc;

    VacationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
