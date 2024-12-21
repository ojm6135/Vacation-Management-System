package com.ojm.vacation_management.form;

import com.ojm.vacation_management.vo.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class UserRegistrationForm {
    @NotBlank(message = "아이디가 필요합니다.")
    @Length(min = 8, max = 15, message = "형식이 맞지 않습니다.")
    @Pattern(regexp = "[\\w_.-]+", message = "형식이 맞지 않습니다.")
    private final String username;

    @NotBlank(message = "비밀번호가 필요합니다.")
    @Length(min = 10, max = 15, message = "형식이 맞지 않습니다.")
    @Pattern(regexp = "[\\w!@#$%^&*_=+.,?-]+", message = "형식이 맞지 않습니다.")
    private final String password;

    @NotBlank(message = "이름이 필요합니다.")
    @Pattern(regexp = "\\S[a-zA-z가-힣 ]+\\S$", message = "형식이 맞지 않습니다.")
    private final String name;

    @NotNull(message = "회원 유형이 필요합니다.")
    private final UserRole role;
}
