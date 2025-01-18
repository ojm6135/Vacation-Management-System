package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.VacationDto;
import com.ojm.vacation_management.repository.VacationRepository;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@customAuth.isOwner(#userId, authentication)")
    @Override
    public void apply(int userId, VacationDto vacationDto) {
        vacationDto.setStatus(AppliedVacationStatus.PENDING);
        vacationRepository.save(VacationDto.toEntity(vacationDto));
    }

    @PreAuthorize("@customAuth.isOwner(#userId, authentication)")
    @Override
    public List<VacationDto> getAllVacationsByUserId(int userId) {
        return vacationRepository.findAllByUserId(userId)
                .stream().map(VacationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @PreAuthorize("@customAuth.isOwner(#userId, authentication)")
    @Override
    public void updateVacation(int userId, int vacationId, VacationDto vacationDto) {
        if (!exists(vacationId)) {
            throw new EntityNotFoundException("휴가를 찾을 수 없습니다. (id: " + vacationId + ")");
        }
        vacationRepository.update(vacationId, VacationDto.toEntity(vacationDto));
    }

    @PreAuthorize("@customAuth.isOwner(#userId, authentication)")
    @Override
    public void deleteVacation(int userId, int vacationId) {
        if (!exists(vacationId)) {
            throw new EntityNotFoundException("휴가를 찾을 수 없습니다. (id: " + vacationId + ")");
        }
        vacationRepository.deleteById(vacationId);
    }

    @Override
    public boolean exists(int vacationId) {
        return vacationRepository.findById(vacationId).isPresent();
    }
}
