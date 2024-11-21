package com.ojm.vacation_management.vo.vacation;

import lombok.Getter;

@Getter
public enum AppliedVacationStatus {
    PENDING(0, "대기중"),
    APPROVED(1, "승인"),
    REJECTED(2, "반려");

    private final int code;
    private final String desc;

    AppliedVacationStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
