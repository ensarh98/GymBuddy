package com.gymbuddy.controller;

import com.gymbuddy.auth.AuthService;
import com.gymbuddy.auth.AuthenticationResponse;
import com.gymbuddy.db.model.UserRecord;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRecord request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserRecord request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return authService.confirmEmail(confirmationToken);
    }

}
