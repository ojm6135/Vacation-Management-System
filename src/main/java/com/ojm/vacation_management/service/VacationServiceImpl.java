package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.VacationDto;
import com.ojm.vacation_management.repository.VacationRepository;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public void apply(VacationDto vacationDto) {
        vacationDto.setStatus(AppliedVacationStatus.PENDING);
        vacationRepository.save(VacationDto.toEntity(vacationDto));
    }

    @Override
    public List<VacationDto> findAllByUserId(int userId) {
        return vacationRepository.findAllByUserId(userId)
                .stream().map(VacationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateVacation(VacationDto vacationDto) {
        if (vacationRepository.findById(vacationDto.getId()).isEmpty()) {
            throw new EntityNotFoundException("휴가를 찾을 수 없습니다. (id: " + vacationDto.getId() + ")");
        }
        vacationRepository.update(VacationDto.toEntity(vacationDto));
    }

    @Override
    public void deleteVacation(int vacationId) {
        if (vacationRepository.findById(vacationId).isEmpty()) {
            throw new EntityNotFoundException("휴가를 찾을 수 없습니다. (id: " + vacationId + ")");
        }
        vacationRepository.deleteById(vacationId);
    }
}
