package com.ojm.vacation_management.vo.vacation;

import com.ojm.vacation_management.exceptions.InvalidDateRangeException;

import java.time.LocalDate;

public record VacationPeriod(LocalDate startDate, LocalDate endDate) {
    public VacationPeriod {
        validate(startDate, endDate);

    }

    private boolean validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("시작일은 종료일 이전이어야 합니다.");
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationPeriod that = (VacationPeriod) o;
        return startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public String toString() {
        return "VacationPeriod[" +
                "startDate=" + startDate + ", " +
                "endDate=" + endDate + ']';
    }


}