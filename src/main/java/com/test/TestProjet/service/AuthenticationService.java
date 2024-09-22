package com.test.TestProjet.service;



import com.test.TestProjet.dtos.LoginUserDto;
import com.test.TestProjet.dtos.RegisterUserDto;
import com.test.TestProjet.entity.Person;
import com.test.TestProjet.repository.PersonRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	
    private final PersonRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        PersonRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person signup(RegisterUserDto input) {
        Person user = new Person();
                user.setUsername(input.getUsername());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }
    public Person authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
    }