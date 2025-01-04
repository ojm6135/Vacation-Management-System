package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.VacationDto;

import java.util.List;

public interface VacationService {
    void apply(VacationDto vacationDto);
    List<VacationDto> findAllByUserId(int userId);
}
