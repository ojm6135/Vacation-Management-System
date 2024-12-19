package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.dto.UserDto;
import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.form.UserRegistrationForm;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    List<User> findAllByStatus(UserStatus status);
    void updateUserRole(int id, UserRole updatedRole);
    void updateUserStatus(int id, UserStatus updatedStatus);
}
