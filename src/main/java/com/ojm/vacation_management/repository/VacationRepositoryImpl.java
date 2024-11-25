package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.QVacation;
import com.ojm.vacation_management.domain.Vacation;
import com.ojm.vacation_management.vo.vacation.AppliedVacationStatus;
import com.ojm.vacation_management.vo.vacation.QVacationPeriod;
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

    @Autowired
    public VacationRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    @Override
    public void save(Vacation vacation) {
        em.persist(vacation);
    }

    @Override
    public List<Vacation> findAll() {
        return em.createQuery("select v from Vacation v", Vacation.class)
                .getResultList();
    }

    @Override
    public List<Vacation> findAllByUserId(int userId) {
        return em.createQuery("select v from Vacation v where v.userId = :userId", Vacation.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Vacation> findAllByPeriod(VacationPeriod target) {
        QVacation vacation = QVacation.vacation;

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
        Vacation target = em.find(Vacation.class, id);
        target.setStatus(status);
    }

    @Override
    public void updatePeriod(int id, VacationPeriod period) {
        Vacation target = em.find(Vacation.class, id);
        target.setPeriod(period);
    }

    @Override
    public void deleteById(int id) {
        em.remove(em.find(Vacation.class, id));
    }
}
