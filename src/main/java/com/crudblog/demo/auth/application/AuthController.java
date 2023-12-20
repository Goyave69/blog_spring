package com.crudblog.demo.auth.application;

import com.crudblog.demo.auth.domain.entity.User;
import com.crudblog.demo.auth.domain.service.JwtTokenService;
import com.crudblog.demo.auth.domain.service.UserDetailsServiceImpl;
import com.crudblog.demo.auth.domain.service.UserLoginService;
import com.crudblog.demo.auth.domain.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserLoginService userLoginService;
    private final JwtTokenService jwtTokenService;
    private final UserDetailsServiceImpl userDetailsService;
    private UserRegistrationService userRegistrationService;

    public AuthController(
            UserLoginService userLoginService,
            JwtTokenService jwtTokenService,
            UserDetailsServiceImpl userDetailsService,
            UserRegistrationService userRegistrationService
    ) {
        this.userLoginService = userLoginService;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userBody) throws Exception {
        try {
            userLoginService.login(userBody);
            String token = jwtTokenService.generateToken(userDetailsService.loadUserByUsername(userBody.getUsername()));

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userBody) throws Exception {
        try {
            return ResponseEntity.status(201).body(userRegistrationService.UserRegistration(userBody));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).build();
        }
    }

}