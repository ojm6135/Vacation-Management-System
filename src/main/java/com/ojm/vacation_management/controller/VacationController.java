package com.ojm.vacation_management.controller;

import com.ojm.vacation_management.dto.VacationDto;
import com.ojm.vacation_management.form.VacationForm;
import com.ojm.vacation_management.form.VacationUpdateForm;
import com.ojm.vacation_management.service.VacationService;
import com.ojm.vacation_management.vo.vacation.VacationType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/{userId}/vacations")
public class VacationController {
    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping
    public String applyVacation(final @PathVariable("userId") int userId, final @Valid VacationForm vacationForm) {
        vacationService.apply(VacationDto.fromForm(vacationForm));
        return "redirect:/users/" + userId + "/vacations";
    }

    @GetMapping
    public String getMyVacations(final @PathVariable("userId") int userId, Model model) {
        model.addAttribute("vacations", vacationService.getAllVacationsByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("types", VacationType.values());
        return "my-vacations";
    }

    @PutMapping("/{vacationId}")
    public String updateVacation(final @PathVariable("userId") int userId,
                                 final @PathVariable("vacationId") int vacationId,
                                 final @Valid VacationUpdateForm form) {
        vacationService.updateVacation(vacationId, VacationDto.fromForm(form));
        return "redirect:/users/" + userId + "/vacations";
    }

    @DeleteMapping("/{vacationId}")
    public String deleteVacation(final @PathVariable("userId") int userId,
                                 final @PathVariable("vacationId") int vacationId) {
        vacationService.deleteVacation(vacationId);
        return "redirect:/users/" + userId + "/vacations";
    }
}
