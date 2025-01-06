package com.ojm.vacation_management.form;

import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VacationUpdateForm {
    @NotNull(message = "휴가 id가 필요합니다.")
    private final int id;

    @NotNull(message = "시작 날짜가 입력되지 않았습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @NotNull(message = "종료 날짜가 입력되지 않았습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

    @NotNull(message = "휴가 종류가 입력되지 않았습니다.")
    private final VacationType type;

    @Nullable
    private final String reason;
}