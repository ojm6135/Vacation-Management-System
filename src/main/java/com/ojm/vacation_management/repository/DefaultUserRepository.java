package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserStatus;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultUserRepository implements UserRepository {
    private final EntityManager em;

    @Autowired
    public DefaultUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<User> findAllByStatus(UserStatus status) {
        return em.createQuery("select u from User u where u.status = :status", User.class)
                .setParameter("status", status)
                .getResultList();
    }
}
