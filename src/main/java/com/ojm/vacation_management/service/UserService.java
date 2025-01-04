package com.ojm.vacation_management.service;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.dto.UserDto;
import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;

import java.util.List;

public interface UserService {
    void join(UserRegistrationDto userRegistrationDto);
//    UserDto login(LoginDto loginDto);
    List<User> findUsers();
    UserDto findOneById(int id);
    UserDto findOneByUsername(String username);
    List<User> findUsersByStatus(UserStatus userStatus);
    void modifyUserRole(int id, UserRole role);
    void modifyUserStatus(int id, UserStatus status);
    boolean isCurrentUser(int requestedUserId);
    String generateVacationUrlByCurrentUser();
}
