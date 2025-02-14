package com.ojm.vacation_management.service;

import com.ojm.vacation_management.domain.User;
import com.ojm.vacation_management.dto.UserDto;
import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.repository.UserRepository;
import com.ojm.vacation_management.vo.user.UserRole;
import com.ojm.vacation_management.vo.user.UserStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void join(UserRegistrationDto userRegistrationDto) {
        // 중복 확인
        userRepository.findByUsername(userRegistrationDto.getUsername())
                .ifPresent(u -> {
                    throw new IllegalStateException("해당 아이디가 이미 존재합니다.");
                });

        // 비밀번호 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(userRegistrationDto.getPassword());

        // 비밀번호 재설정
        userRegistrationDto.changeHashedPassword(encodedPassword);

        // 저장
        User user = UserRegistrationDto.toEntity(userRegistrationDto);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findOneById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("해당 아이디를 갖는 회원이 없습니다.");
        }

        return UserDto.fromEntity(userOptional.get());
    }

    @Override
    public UserDto findOneByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("해당 아이디를 갖는 회원이 없습니다.");
        }

        return UserDto.fromEntity(userOptional.get());
    }

    @Override
    public List<User> findUsersByStatus(UserStatus userStatus) {
        return userRepository.findAllByStatus(userStatus);
    }

    @Override
    public void modifyUserRole(int id, UserRole role) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("해당 아이디를 갖는 회원이 존재하지 않습니다.");
        }

        userRepository.updateUserRole(id, role);
    }

    @Override
    public void modifyUserStatus(int id, UserStatus status) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("해당 아이디를 갖는 회원이 존재하지 않습니다.");
        }

        userRepository.updateUserStatus(id, status);
    }
}
