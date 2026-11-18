package com.hari.Expense_Tracker.service;

import com.hari.Expense_Tracker.DTO.LoginRequest;
import com.hari.Expense_Tracker.DTO.SignUpRequest;
import com.hari.Expense_Tracker.entity.User;
import com.hari.Expense_Tracker.exception.ResourceAlreadyExistsException;
import com.hari.Expense_Tracker.exception.ResourceNotFoundException;
import com.hari.Expense_Tracker.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.NameNotFoundException;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public ResponseEntity<String> signup(SignUpRequest request){
        String email=request.getEmailId().trim();

        if(userRepo.existsByEmailId(email)){
            throw new ResourceAlreadyExistsException("Already email id registered");
        }
        User user=new User(request.getUserName(), request.getEmailId(), passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);

        return ResponseEntity.ok("Successfully Signed Up");

    }

    public ResponseEntity<String> login(LoginRequest request){
        String email= request.getEmailId().trim();

        if(!userRepo.existsByEmailId(email)){
            throw new ResourceNotFoundException("User does not exist");
        }

        User user=userRepo.findByEmailId(email);

        boolean passwordMatch=passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(passwordMatch){
            String token=jwtService.GenerateToken(user.getEmailId());
            return ResponseEntity.ok(token);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
