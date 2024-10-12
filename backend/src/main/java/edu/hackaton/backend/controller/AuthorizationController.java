package edu.hackaton.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.hackaton.backend.model.Gender;
import edu.hackaton.backend.model.Role;
import edu.hackaton.backend.model.User;
import edu.hackaton.backend.security.TokenManager;
import edu.hackaton.backend.security.UserInfo;
import edu.hackaton.backend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthorizationController {

    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("login")
    TokenResponse login(@RequestBody LoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        UserInfo user = (UserInfo) auth.getPrincipal();
        String token = tokenManager.generateToken(user);

        return new TokenResponse(token, user.getRole());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    void register(@RequestBody UserForm userForm) {
        // map form to user
        User user = User.builder().firstName(userForm.firstName())
            .lastName(userForm.lastName)
            .email(userForm.email())
            .password(passwordEncoder.encode(userForm.password()))
            .birthDate(userForm.birthDate())
            .userName(userForm.userName())
            .phoneNumber(userForm.phoneNumber())
            .role(userForm.role())
            .gender(userForm.gender()).build();
        userService.addUser(user);
    }

    @GetMapping("test")
    String getMethodName() {
        return "test";
    }

    @GetMapping("me")
    Object getMe(Authentication authentication) {
        return authentication.getPrincipal();
    }
    
    
    record LoginRequest(String email, String password){}
    record TokenResponse(String accessToken, Role role){}

    record UserForm(String firstName, String lastName, String email, String password, LocalDate birthDate, String userName, String phoneNumber, Role role, Gender gender) {}
}
