package com.nawid.onlineshop.service;


import com.nawid.onlineshop.dto.LoginDto;
import com.nawid.onlineshop.dto.RegisterDto;
import com.nawid.onlineshop.entity.User;
import com.nawid.onlineshop.exception.UserAlreadyExistsException;
import com.nawid.onlineshop.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepo.existsByEmail(registerDto.getEmail())){
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,"email already exists");
        }
        if(userRepo.existsByUsername(registerDto.getUsername())){
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,"username already exists");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());
        user.setRole("ROLE_USER");
        userRepo.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }
}
