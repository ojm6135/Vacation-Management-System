package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserStatus;

import java.util.List;

public interface UserRepository {
    public void save(User user);
    public List<User> findAll();
    public List<User> findAllByStatus(UserStatus status);
}
