package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.QVacation;
import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.VacationPeriod;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacationRepositoryImpl implements VacationRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QVacation vacation;

    @Autowired
    public VacationRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
        this.vacation = QVacation.vacation;
    }

    @Override
    public void save(Vacation vacation) {
        em.persist(vacation);
    }

    @Override
    public List<Vacation> findAll() {
        return queryFactory.selectFrom(vacation)
                .fetch();
    }

    @Override
    public List<Vacation> findAllByUserId(int userId) {
        return queryFactory.selectFrom(vacation)
                .where(vacation.userId.eq(userId))
                .fetch();
    }

    @Override
    public List<Vacation> findAllByPeriod(VacationPeriod target) {
        return queryFactory.selectFrom(vacation)
                .where(vacation.period.startDate
                        .after(target.getEndDate())
                        .not()
                        .and(vacation.period.endDate
                                        .before(target.getStartDate())
                                .not()))
                .fetch();
    }

    @Override
    public void updateStatus(int id, AppliedVacationStatus status) {

    }

    @Override
    public void updatePeriod(int id, VacationPeriod period) {

    }

    @Override
    public void deleteById(int id) {
        queryFactory.delete(vacation)
                        .where(vacation.id.eq(id));
    }
}
