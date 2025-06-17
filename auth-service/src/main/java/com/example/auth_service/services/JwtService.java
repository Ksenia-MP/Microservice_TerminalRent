package com.example.auth_service.services;

import com.example.auth_service.RegistrationUtil;
import com.example.auth_service.dtos.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final RegistrationUtil registrationUtil;

    public JwtService(AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService, RegistrationUtil registrationUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.registrationUtil = registrationUtil;
    }

    public String createJwtToken(AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return registrationUtil.generateToken(userDetails);
    }
}