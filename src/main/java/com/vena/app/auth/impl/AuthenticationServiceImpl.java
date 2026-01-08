package com.vena.app.auth.impl;

import com.vena.app.auth.AuthenticationService;
import com.vena.app.auth.request.AuthenticationRequest;
import com.vena.app.auth.request.RefreshRequest;
import com.vena.app.auth.response.AuthenticationResponse;
import com.vena.app.role.RoleRepository;
import com.vena.app.security.JwtService;
import com.vena.app.user.User;
import com.vena.app.user.UserMapper;
import com.vena.app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = (User) auth.getPrincipal();
        final String token = this.jwtService.generateAccessToken(user.getUsername());
        final String refreshToken = this.jwtService.generateRefreshToken(user.getUsername());
        final String tokenType = "Bearer";

        return null;
    }

    @Override
    public void register(AuthenticationRequest request) {

    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        return null;
    }
}
