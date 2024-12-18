package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.form.UserRegistrationForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(final @Valid UserRegistrationForm userRegistrationForm) {
        System.out.println("userRegistrationForm = " + userRegistrationForm);
        return "redirect:/";
    }
}
