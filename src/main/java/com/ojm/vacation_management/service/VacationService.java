package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.VacationDto;

import java.util.List;

public interface VacationService {
    void apply(int userId, VacationDto vacationDto);
    List<VacationDto> getAllVacationsByUserId(int userId);
    void updateVacation(int userId, int vacationId, VacationDto vacationDto);
    void deleteVacation(int userId, int vacationId);
    boolean exists(int vacationId);
}
