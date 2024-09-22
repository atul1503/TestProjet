package com.test.TestProjet.controller;


import com.test.TestProjet.entity.Person;
import com.test.TestProjet.dtos.LoginUserDto;
import com.test.TestProjet.dtos.RegisterUserDto;
import com.test.TestProjet.dtos.LoginResponse;
import com.test.TestProjet.service.AuthenticationService;
import com.test.TestProjet.service.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;
    
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Person> register(@RequestBody RegisterUserDto registerUserDto) {
        Person registeredPerson = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Person authenticatedPerson = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedPerson);
        

        LoginResponse loginResponse = new LoginResponse();
        		loginResponse.setToken(jwtToken);
        		loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
