package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.QUser;
import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    private final QUser user;

    @Autowired
    public UserRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
        this.user = QUser.user;
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User u = queryFactory.selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();

        return Optional.ofNullable(u);
    }

    @Override
    public List<User> findAll() {
        return queryFactory.selectFrom(user).fetch();
    }

    @Override
    public List<User> findAllByStatus(UserStatus status) {
        return queryFactory.selectFrom(user)
                .where(user.status.eq(status))
                .fetch();
    }

    @Override
    public void updateUserRole(int id, UserRole updatedRole) {
        User target = em.find(User.class, id);
        target.changeRole(updatedRole);
    }

    @Override
    public void updateUserStatus(int id, UserStatus updatedStatus) {
        User target = em.find(User.class, id);
        target.changeStatus(updatedStatus);
    }
}

