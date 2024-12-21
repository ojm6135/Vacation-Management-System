package com.ojm.vacation_management.dto;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.ojm.vacation_management.vo.vacation.VacationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacationDto {
    private int id;
    private int userId;
    private VacationPeriod period;
    private VacationType type;
    private String reason;
    private AppliedVacationStatus status;

    public VacationDto(Vacation vacation) {
        this.id = vacation.getId();
        this.userId = vacation.getUserId();
        this.period = vacation.getPeriod();
        this.type = vacation.getType();
        this.reason = vacation.getReason();
        this.status = vacation.getStatus();
    }
}
