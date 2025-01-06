package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;

import java.util.List;
import java.util.Optional;

public interface VacationRepository {
    void save(Vacation vacation);
    Optional<Vacation> findById(int id);
    List<Vacation> findAll();
    List<Vacation> findAllByUserId(int userId);
    List<Vacation> findAllByPeriod(VacationPeriod period);
    void update(int vacationId, Vacation vacation);
    void deleteById(int id);
}
