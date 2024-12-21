package com.ojm.vacation_management.service;

import com.ojm.vacation_management.dto.UserDto;
import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.vo.user.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {
    private final UserService userService;

    @Autowired
    UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void join() {
        // given
        UserRegistrationDto userDto1 = UserRegistrationDto.builder()
                .name("박보영")
                .username("bbovly")
                .password("park1234!!")
                .role(UserRole.ADMIN)
                .build();

        userService.join(userDto1);

        // when
        UserDto result = userService.findOneByUsername("bbovly");

        // then
        assertThat(result.getName()).isEqualTo(userDto1.getName());
        assertThat(result.getUsername()).isEqualTo(userDto1.getUsername());
        assertThat(result.getRole()).isEqualTo(userDto1.getRole());
    }

    @Test
    void validateDuplicatedUser() {
        // given
        UserRegistrationDto userDto1 = UserRegistrationDto.builder()
                .name("박보영")
                .username("bbovly")
                .password("park1234!!")
                .role(UserRole.ADMIN)
                .build();

        UserRegistrationDto userDto2 = UserRegistrationDto.builder()
                .name("권지용")
                .username("bbovly")
                .password("gdgdbabybaby!")
                .role(UserRole.MEMBER)
                .build();

        userService.join(userDto1);

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.join(userDto2));
    }

    @Test
    void findUsers() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findUsersByStatus() {
    }

    @Test
    void modifyUserRole() {
    }

    @Test
    void modifyUserStatus() {
    }
}