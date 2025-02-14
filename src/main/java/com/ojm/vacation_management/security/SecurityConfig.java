package com.ojm.vacation_management.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/users/login", "/users/signup").permitAll()
                        .requestMatchers("/static/css/**", "/static/js/**").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .csrf((auth) -> auth.disable());

        http
                .formLogin((auth) -> auth.loginPage("/users/login")
                        .loginProcessingUrl("/users/login").permitAll()
                        .defaultSuccessUrl("/users/logged-in")
                );

        return http.build();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring()
//                .requestMatchers("/static/css/**", "/static/js/**");
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}