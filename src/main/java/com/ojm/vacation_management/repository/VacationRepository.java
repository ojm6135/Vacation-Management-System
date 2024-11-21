package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;

import java.time.LocalDate;
import java.util.List;

public interface VacationRepository {
    public void save(Vacation vacation);
    public List<Vacation> findAll();
    public List<Vacation> findAllByUserId(Long userId);
    public List<Vacation> findAllByDate(LocalDate date);
    public void updatePeriod(int id, VacationPeriod period);
    public void deleteById(int id);
}
