package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryImplTest {
    private final UserRepository userRepository;
    private User user1;
    private User user2;
    private User user3;

    @Autowired
    UserRepositoryImplTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void BeforeEach() {
        user1 = User.builder()
                .name("박보영")
                .username("bbovly")
                .password("park1234!!")
                .role(UserRole.ADMIN)
                .status(UserStatus.ON_VACATION)
                .build();

        user2 = User.builder()
                .name("권지용")
                .username("xxxibgdrgn")
                .password("gdgdbabybaby")
                .role(UserRole.MEMBER)
                .status(UserStatus.ON_WORK)
                .build();

        user3 = User.builder()
                .name("유지민")
                .username("realkarina")
                .password("winter123@")
                .role(UserRole.MEMBER)
                .status(UserStatus.ON_WORK)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    void save() {
        // given
        User user = User.builder()
                .name("박명수")
                .username("geosung")
                .password("greatpark!")
                .role(UserRole.ADMIN)
                .status(UserStatus.ON_VACATION)
                .build();

        // when
        userRepository.save(user);

        // then
        User result = userRepository.findById(user.getId()).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void findById() {
        // given

        // when
        User result1 = userRepository.findById(user1.getId()).get();
        User result2 = userRepository.findById(user2.getId()).get();

        // then
        Assertions.assertThat(result1).isEqualTo(user1);
        Assertions.assertThat(result2).isEqualTo(user2);
    }

    @Test
    void findByUsername() {
        // given

        // when
        User result = userRepository.findByUsername("xxxibgdrgn").get();

        // then
        Assertions.assertThat(result.getUsername()).isEqualTo("xxxibgdrgn");
    }

    @Test
    void findAll() {
        // given

        // when
        List<User> result = userRepository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void findAllByStatus() {
        // given

        // when
        List<User> resultOnWork = userRepository.findAllByStatus(UserStatus.ON_WORK);
        List<User> resultOnVacation = userRepository.findAllByStatus(UserStatus.ON_VACATION);

        // then
        Assertions.assertThat(resultOnWork.size()).isEqualTo(2);
        Assertions.assertThat(resultOnVacation.size()).isEqualTo(1);
    }

    @Test
    void updateUserRole() {
        // given

        // when
        userRepository.updateUserRole(user1.getId(), UserRole.MEMBER);
        userRepository.updateUserRole(user2.getId(), UserRole.ADMIN);

        // then
        Assertions.assertThat(user1.getRole()).isEqualTo(UserRole.MEMBER);
        Assertions.assertThat(user2.getRole()).isEqualTo(UserRole.ADMIN);
    }

    @Test
    void updateUserStatus() {
        // given

        // when
        userRepository.updateUserStatus(user1.getId(), UserStatus.ON_WORK);
        userRepository.updateUserStatus(user2.getId(), UserStatus.ON_VACATION);

        // then
        Assertions.assertThat(user1.getStatus()).isEqualTo(UserStatus.ON_WORK);
        Assertions.assertThat(user2.getStatus()).isEqualTo(UserStatus.ON_VACATION);
    }
}