package com.ojm.vacation_management.domain;

import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;
    private final String username;
    private final String password;
    private final String name;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    public void changeRole(UserRole role) {
        this.role = role;
    }

    public void changeStatus(UserStatus status) {
        this.status = status;
    }
}
