package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.form.UserRegistrationForm;
import com.ojm.vacation_management.service.UserService;
import com.ojm.vacation_management.vo.user.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("roles", UserRole.values());
        return "signup";
    }

    @PostMapping("/signup")
    public String register(final @Valid UserRegistrationForm userRegistrationForm) {
        userService.join(UserRegistrationDto.fromForm(userRegistrationForm));
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(final @Valid LoginDto loginDto) {
//        return "redirect:/vacations?user_id=" + userService.login(loginDto).getId();
//    }

    @GetMapping("/{userId}")
    public String getUserById(final @PathVariable int userId, Model model) {
        // 내 정보 확인.
        return "";
    }
}
