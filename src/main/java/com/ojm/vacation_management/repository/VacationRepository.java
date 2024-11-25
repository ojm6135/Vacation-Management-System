package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;

import java.time.LocalDate;
import java.util.List;

public interface VacationRepository {
    void save(Vacation vacation);
    List<Vacation> findAll();
    List<Vacation> findAllByUserId(int userId);
    List<Vacation> findAllByPeriod(VacationPeriod period);
    void updateStatus(int id, AppliedVacationStatus status);
    void updatePeriod(int id, VacationPeriod period);
    void deleteById(int id);
}
