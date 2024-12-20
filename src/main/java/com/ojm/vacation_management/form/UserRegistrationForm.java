package com.ojm.vacation_management.form;

import com.ojm.vacation_management.vo.user.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class UserRegistrationForm {
    @Length(min = 8, max = 15)
    @Pattern(regexp = "[\\w_.-]+")
    private final String username;

    @Length(min = 10, max = 15)
    @Pattern(regexp = "[\\w!@#$%^&*_=+.,?-]+")
    private final String password;

    @Pattern(regexp = "\\S[a-zA-z가-힣 ]+\\S$")
    private final String name;

    @NotNull
    private final UserRole role;

    @Override
    public String toString() {
        return "UserRegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
