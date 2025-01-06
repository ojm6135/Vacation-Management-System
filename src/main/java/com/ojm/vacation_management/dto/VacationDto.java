package com.ojm.vacation_management.dto;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.form.VacationForm;
import com.ojm.vacation_management.form.VacationUpdateForm;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.ojm.vacation_management.vo.vacation.VacationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VacationDto {
    private int id;
    private int userId;
    private VacationPeriod period;
    private VacationType type;
    private String reason;
    private AppliedVacationStatus status;

    public static Vacation toEntity(VacationDto dto) {
        return Vacation.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .period(dto.getPeriod())
                .type(dto.getType())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .build();
    }

    public static VacationDto fromEntity(Vacation entity) {
        return VacationDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .period(entity.getPeriod())
                .type(entity.getType())
                .reason(entity.getReason())
                .status(entity.getStatus())
                .build();
    }

    public static VacationDto fromForm(VacationForm form) {
        VacationPeriod period = new VacationPeriod(form.getStartDate(), form.getEndDate());

        return VacationDto.builder()
                .userId(form.getUserId())
                .period(period)
                .type(form.getType())
                .reason(form.getReason())
                .build();
    }

    public static VacationDto fromForm(VacationUpdateForm form) {
        VacationPeriod period = new VacationPeriod(form.getStartDate(), form.getEndDate());

        return VacationDto.builder()
                .period(period)
                .type(form.getType())
                .reason(form.getReason())
                .build();
    }
}
