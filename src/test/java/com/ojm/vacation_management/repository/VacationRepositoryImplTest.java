package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.ojm.vacation_management.vo.vacation.VacationType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class VacationRepositoryImplTest {

    private final VacationRepository vacationRepository;

    @Autowired
    VacationRepositoryImplTest(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Test
    void save() {
        // given
        Vacation vacation = new Vacation();
        vacation.setUserId(1);
        vacation.setType(VacationType.REGULAR_VACATION);
        vacation.setStatus(AppliedVacationStatus.PENDING);
        vacation.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));

        // when
        vacationRepository.save(vacation);

        // then
        List<Vacation> result = vacationRepository.findAllByUserId(1);
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0)).isEqualTo(vacation);
    }

    @Test
    void findAll() {
        // given
        Vacation vacation1 = new Vacation();
        vacation1.setUserId(1);
        vacation1.setType(VacationType.REGULAR_VACATION);
        vacation1.setStatus(AppliedVacationStatus.APPROVED);
        vacation1.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));

        Vacation vacation2 = new Vacation();
        vacation2.setUserId(2);
        vacation2.setType(VacationType.EMERGENCY_LEAVE);
        vacation2.setReason("그냥");
        vacation2.setStatus(AppliedVacationStatus.PENDING);
        vacation2.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 8)));

        Vacation vacation3 = new Vacation();
        vacation3.setUserId(3);
        vacation3.setType(VacationType.SPECIAL_VACATION);
        vacation3.setStatus(AppliedVacationStatus.PENDING);
        vacation3.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 20), LocalDate.of(2024, 11, 23)));

        vacationRepository.save(vacation1);
        vacationRepository.save(vacation2);
        vacationRepository.save(vacation3);

        // when
        List<Vacation> result = vacationRepository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void findAllByUserId() {
        // given
        Vacation vacation1 = new Vacation();
        vacation1.setUserId(1);
        vacation1.setType(VacationType.REGULAR_VACATION);
        vacation1.setStatus(AppliedVacationStatus.APPROVED);
        vacation1.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));

        Vacation vacation2 = new Vacation();
        vacation2.setUserId(1);
        vacation2.setType(VacationType.EMERGENCY_LEAVE);
        vacation2.setReason("그냥");
        vacation2.setStatus(AppliedVacationStatus.PENDING);
        vacation2.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 8)));

        Vacation vacation3 = new Vacation();
        vacation3.setUserId(3);
        vacation3.setType(VacationType.SPECIAL_VACATION);
        vacation3.setStatus(AppliedVacationStatus.PENDING);
        vacation3.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 20), LocalDate.of(2024, 11, 23)));

        vacationRepository.save(vacation1);
        vacationRepository.save(vacation2);
        vacationRepository.save(vacation3);

        // when
        List<Vacation> result = vacationRepository.findAllByUserId(1);

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findAllByPeriod() {
        // given
        Vacation vacation1 = new Vacation();
        vacation1.setUserId(1);
        vacation1.setType(VacationType.REGULAR_VACATION);
        vacation1.setStatus(AppliedVacationStatus.APPROVED);
        vacation1.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));

        Vacation vacation2 = new Vacation();
        vacation2.setUserId(2);
        vacation2.setType(VacationType.EMERGENCY_LEAVE);
        vacation2.setReason("그냥");
        vacation2.setStatus(AppliedVacationStatus.PENDING);
        vacation2.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 8)));

        Vacation vacation3 = new Vacation();
        vacation3.setUserId(3);
        vacation3.setType(VacationType.SPECIAL_VACATION);
        vacation3.setStatus(AppliedVacationStatus.PENDING);
        vacation3.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 20), LocalDate.of(2024, 11, 23)));

        vacationRepository.save(vacation1);
        vacationRepository.save(vacation2);
        vacationRepository.save(vacation3);

        VacationPeriod unMatchedPeriod = new VacationPeriod(LocalDate.of(2010, 11, 5), LocalDate.of(2010, 11, 15));
        VacationPeriod matchedPeriod1 = new VacationPeriod(LocalDate.of(2024, 11, 2), LocalDate.of(2024, 11, 21));
        VacationPeriod matchedPeriod2 = new VacationPeriod(LocalDate.of(2024, 11, 3), LocalDate.of(2024, 11, 15));

        // when
        List<Vacation> unMatchedResult = vacationRepository.findAllByPeriod(unMatchedPeriod);
        List<Vacation> matchedResult1 = vacationRepository.findAllByPeriod(matchedPeriod1);
        List<Vacation> matchedResult2 = vacationRepository.findAllByPeriod(matchedPeriod2);

        // then
        Assertions.assertThat(unMatchedResult.size()).isEqualTo(0);
        Assertions.assertThat(matchedResult1.size()).isEqualTo(3);
        Assertions.assertThat(matchedResult2.size()).isEqualTo(2);
    }

    @Test
    void updateStatus() {
        // given
        Vacation vacation = new Vacation();
        vacation.setUserId(1);
        vacation.setType(VacationType.REGULAR_VACATION);
        vacation.setStatus(AppliedVacationStatus.PENDING);
        vacation.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));
        vacationRepository.save(vacation);

        // when
        vacationRepository.updateStatus(vacation.getId(), AppliedVacationStatus.REJECTED);

        // then
        Assertions.assertThat(vacation.getStatus()).isEqualTo(AppliedVacationStatus.REJECTED);
    }

    @Test
    void updatePeriod() {
        // given
        Vacation vacation = new Vacation();
        vacation.setUserId(1);
        vacation.setType(VacationType.REGULAR_VACATION);
        vacation.setStatus(AppliedVacationStatus.PENDING);
        vacation.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));
        vacationRepository.save(vacation);

        // when
        VacationPeriod updatedPeriod = new VacationPeriod(LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26));
        vacationRepository.updatePeriod(vacation.getId(), updatedPeriod);

        // then
        Assertions.assertThat(vacation.getPeriod()).isEqualTo(updatedPeriod);
    }

    @Test
    void deleteById() {
        // given
        Vacation vacation = new Vacation();
        vacation.setUserId(1);
        vacation.setType(VacationType.REGULAR_VACATION);
        vacation.setStatus(AppliedVacationStatus.PENDING);
        vacation.setPeriod(new VacationPeriod(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 5)));
        vacationRepository.save(vacation);

        // when
        vacationRepository.deleteById(vacation.getId());

        // then
        List<Vacation> result = vacationRepository.findAllByUserId(vacation.getUserId());
        Assertions.assertThat(result).isNullOrEmpty();
    }
}