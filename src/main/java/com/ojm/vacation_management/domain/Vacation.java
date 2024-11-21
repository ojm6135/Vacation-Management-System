package com.ojm.vacation_management.domain;

import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private VacationPeriod period;
    private VacationType type;
    private String reason;
    private AppliedVacationStatus status;
}
