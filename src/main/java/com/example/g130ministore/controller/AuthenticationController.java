package com.example.g130ministore.controller;

import com.example.g130ministore.dto.AuthenticationRequest;
import com.example.g130ministore.dto.AuthenticationResponse;
import com.example.g130ministore.dto.RefreshTokenRequest;
import com.example.g130ministore.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String accessToken = jwtUtil.generateAccessToken(userDetails);
            final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
        } catch (RuntimeException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        String username = jwtUtil.extractUsername(refreshToken);

        if (username != null && jwtUtil.validateToken(refreshToken, userDetailsService.loadUserByUsername(username))) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtUtil.generateAccessToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(newAccessToken, refreshToken));
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}