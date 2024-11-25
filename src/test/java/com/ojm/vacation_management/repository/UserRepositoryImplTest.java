package com.ojm.vacation_management.repository;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryImplTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryImplTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void save() {
        // given
        User user = new User();
        user.setName("koo");
        user.setPassword("1q2w3e4r!");
        user.setRole(UserRole.MEMBER);
        user.setStatus(UserStatus.ON_WORK);

        // when
        userRepository.save(user);

        // then
        User result = userRepository.findById(user.getId()).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void findById() {
        // given
        User user1 = new User();
        user1.setName("koo");
        user1.setPassword("1q2w3e4r!");
        user1.setRole(UserRole.MEMBER);
        user1.setStatus(UserStatus.ON_WORK);

        User user2 = new User();
        user2.setName("goo");
        user2.setPassword("1q2w3e4r!");
        user2.setRole(UserRole.MEMBER);
        user2.setStatus(UserStatus.ON_VACATION);

        userRepository.save(user1);
        userRepository.save(user2);

        // when
        User result1 = userRepository.findById(user1.getId()).get();
        User result2 = userRepository.findById(user2.getId()).get();

        // then
        Assertions.assertThat(result1).isEqualTo(user1);
        Assertions.assertThat(result2).isEqualTo(user2);
    }

    @Test
    void findAll() {
        // given
        User user1 = new User();
        user1.setName("koo");
        user1.setPassword("1q2w3e4r!");
        user1.setRole(UserRole.MEMBER);
        user1.setStatus(UserStatus.ON_WORK);

        User user2 = new User();
        user2.setName("goo");
        user2.setPassword("1q2w3e4r!");
        user2.setRole(UserRole.MEMBER);
        user2.setStatus(UserStatus.ON_VACATION);

        User user3 = new User();
        user3.setName("ooo");
        user3.setPassword("1q2w3e4r!");
        user3.setRole(UserRole.APPROVER);
        user3.setStatus(UserStatus.ON_WORK);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // when
        List<User> result = userRepository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void findAllByStatus() {
        // given
        User user1 = new User();
        user1.setName("koo");
        user1.setPassword("1q2w3e4r!");
        user1.setRole(UserRole.MEMBER);
        user1.setStatus(UserStatus.ON_WORK);

        User user2 = new User();
        user2.setName("goo");
        user2.setPassword("1q2w3e4r!");
        user2.setRole(UserRole.MEMBER);
        user2.setStatus(UserStatus.ON_VACATION);

        User user3 = new User();
        user3.setName("ooo");
        user3.setPassword("1q2w3e4r!");
        user3.setRole(UserRole.APPROVER);
        user3.setStatus(UserStatus.ON_WORK);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // when
        List<User> resultOnWork = userRepository.findAllByStatus(UserStatus.ON_WORK);
        List<User> resultOnVacation = userRepository.findAllByStatus(UserStatus.ON_VACATION);

        // then
        Assertions.assertThat(resultOnWork.size()).isEqualTo(2);
        Assertions.assertThat(resultOnVacation.size()).isEqualTo(1);
    }
}