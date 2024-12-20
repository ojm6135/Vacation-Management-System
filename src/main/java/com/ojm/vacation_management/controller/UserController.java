package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.form.UserRegistrationForm;
import com.ojm.vacation_management.vo.user.UserRole;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("roles", UserRole.values());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(final @Valid UserRegistrationForm userRegistrationForm) {
        System.out.println("userRegistrationForm = " + userRegistrationForm);
        return "redirect:/";
    }
}
