package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.VacationDto;

import java.util.List;

public interface VacationService {
    void apply(VacationDto vacationDto);
    List<VacationDto> getAllVacationsByUserId(int userId);
    void updateVacation(int vacationId, VacationDto vacationDto);
    void deleteVacation(int vacationId);
    boolean exists(int vacationId);
}
