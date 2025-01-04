package com.ojm.vacation_management.domain;

import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "vacations")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int userId;

    @Embedded
    private VacationPeriod period;

    @NotNull
    private VacationType type;

    @Nullable
    private String reason;

    @NotNull
    private AppliedVacationStatus status;

    public void changePeriod(VacationPeriod period) {
        this.period = period;
    }

    public void changeType(VacationType type) {
        this.type = type;
    }

    public void changeReason(String reason) {
        this.reason = reason;
    }

    public void changeStatus(AppliedVacationStatus status) {
        this.status = status;
    }
}
