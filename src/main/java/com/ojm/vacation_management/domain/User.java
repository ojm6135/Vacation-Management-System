package com.ojm.vacation_management.domain;

import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder()
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;
    private final String password;
    private final String name;
    private UserRole role;
    private UserStatus status;

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
