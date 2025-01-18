package com.ojm.vacation_management.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("customAuth")
public class CustomAuthorization {
    public boolean isOwner(int userId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userId == userDetails.getUserId() && authentication.isAuthenticated();
    }
}
