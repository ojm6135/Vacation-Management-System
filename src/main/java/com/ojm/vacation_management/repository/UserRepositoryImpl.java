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
import java.util.NoSuchElementException;
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
        Optional<User> userOptional = findById(id);
        userOptional.ifPresentOrElse(user -> {
            user.setRole(updatedRole);
        }, () -> {
            throw new NoSuchElementException("id가 " + id + "인 회원이 존재하지 않습니다.");
        });
    }

    @Override
    public void updateUserStatus(int id, UserStatus updatedStatus) {
        Optional<User> userOptional = findById(id);
        userOptional.ifPresentOrElse(user -> {
            user.setStatus(updatedStatus);
        }, () -> {
            throw new NoSuchElementException("id가 " + id + "인 회원이 존재하지 않습니다.");
        });
    }
}

