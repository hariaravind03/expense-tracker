package com.hari.Expense_Tracker.controller;

import com.hari.Expense_Tracker.DTO.LoginRequest;
import com.hari.Expense_Tracker.DTO.SignUpRequest;
import com.hari.Expense_Tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {

    public AuthController(AuthService authServ) {
        this.authServ = authServ;
    }

    AuthService authServ;

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@Valid @RequestBody SignUpRequest request) {
        return authServ.signup(request);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser( @Valid @RequestBody LoginRequest request){
        return authServ.login(request);
    }

}