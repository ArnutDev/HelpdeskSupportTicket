package com.arnut.helpdesk.ticketing.controller;

import com.arnut.helpdesk.ticketing.entity.LoginRequest;
import com.arnut.helpdesk.ticketing.entity.LoginResponse;
import com.arnut.helpdesk.ticketing.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return new LoginResponse("Login successful");
        } else {
            return new LoginResponse("Invalid username or password");
        }
    }
}
