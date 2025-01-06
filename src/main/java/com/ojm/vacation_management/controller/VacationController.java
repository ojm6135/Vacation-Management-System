package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.dto.VacationDto;
import com.ojm.vacation_management.form.VacationForm;
import com.ojm.vacation_management.form.VacationUpdateForm;
import com.ojm.vacation_management.service.UserService;
import com.ojm.vacation_management.service.VacationService;
import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String applyVacation(final @Valid VacationForm vacationForm) {
        vacationService.apply(VacationDto.fromForm(vacationForm));
        return "redirect:/vacations?user_id=" + vacationForm.getUserId();
    }

    @GetMapping("/my")
    public String redirectToMyVacations() {
        return "redirect:" + userService.generateVacationUrlByCurrentUser();
    }

    @GetMapping
    public String getMyVacations(final @RequestParam(name = "user_id") int userId, Model model) {
        model.addAttribute("vacations", vacationService.getAllVacationsByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("types", VacationType.values());
        return "my-vacations";
    }

    @PutMapping("/{vacationId}")
    public String updateVacation(final @PathVariable int vacationId, final @Valid VacationUpdateForm form) {
        vacationService.updateVacation(vacationId, VacationDto.fromForm(form));
        return "redirect:/vacations/my";
    }

    @DeleteMapping("/{vacationId}")
    public String deleteVacation(final @PathVariable int vacationId) {
        vacationService.deleteVacation(vacationId);
        return "redirect:/vacations/my";
    }
}
