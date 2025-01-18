package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.dto.CustomUserDetails;
import com.ojm.vacation_management.dto.UserRegistrationDto;
import com.ojm.vacation_management.form.UserRegistrationForm;
import com.ojm.vacation_management.service.UserService;
import com.ojm.vacation_management.vo.user.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{userId}")
    public String getUserById(final @PathVariable int userId, Model model) {
        // todo 내 정보 확인.
        return "";
    }

    @GetMapping("/logged-in")
    public String LoggedIn(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return "redirect:/users/" + userDetails.getUserId() + "/vacations";
    }
}
