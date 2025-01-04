package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.dto.VacationDto;
import com.ojm.vacation_management.form.VacationForm;
import com.ojm.vacation_management.service.UserService;
import com.ojm.vacation_management.service.VacationService;
import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vacations")
public class VacationController {
    private final UserService userService;  // url 생성을 위함.
    private final VacationService vacationService;

    @Autowired
    public VacationController(UserService userService, VacationService vacationService) {
        this.userService = userService;
        this.vacationService = vacationService;
    }

    @PostMapping("/apply")
    public String applyVacation(final @Valid VacationForm vacationForm) {
        System.out.println("vacationForm = " + vacationForm);
        vacationService.apply(VacationDto.fromForm(vacationForm));
        return "redirect:/vacations?user_id=" + vacationForm.getUserId();
    }

    @GetMapping("/my")
    private String redirectToMyVacations() {
        return "redirect:" + userService.generateVacationUrlByCurrentUser();
    }

    @GetMapping
    private String getMyVacations(@RequestParam(name = "user_id") int userId, Model model) {
        if (!userService.isCurrentUser(userId)) {
            return "redirect:/vacations/my";
        }

        model.addAttribute("vacations", vacationService.findAllByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("types", VacationType.values());
        return "my-vacations";
    }
}
