package com.ojm.vacation_management.vo.vacation;

import com.ojm.vacation_management.exceptions.InvalidDateRangeException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public final class VacationPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public VacationPeriod(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private boolean validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("시작일은 종료일 이전이어야 합니다.");
        }

        // 시작날짜가 신청일보다 늦어야 함.
        if (startDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new InvalidDateRangeException("시작일은 신청일 이후여야 합니다.");
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

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}