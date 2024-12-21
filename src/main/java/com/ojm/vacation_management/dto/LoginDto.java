package com.ojm.vacation_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "아이디가 필요합니다..")
    @Length(min = 8, max = 15, message = "입력한 정보가 일치하지 않습니다.")
    @Pattern(regexp = "[\\w_.-]+", message = "입력한 정보가 일치하지 않습니다.")
    private String username;

    @NotBlank(message = "비밀번호가 필요합니다.")
    @Length(min = 10, max = 15, message = "입력한 정보가 일치하지 않습니다.")
    @Pattern(regexp = "[\\w!@#$%^&*_=+.,?-]+", message = "입력한 정보가 일치하지 않습니다.")
    private String password;
}