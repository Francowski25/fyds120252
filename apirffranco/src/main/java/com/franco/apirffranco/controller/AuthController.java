package com.franco.apirffranco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franco.apirffranco.JwtProperties;
import com.franco.apirffranco.JwtService;
import com.franco.apirffranco.model.RefreshToken;
import com.franco.apirffranco.model.User;
import com.franco.apirffranco.service.RefreshTokenService;
import com.franco.apirffranco.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(
            JwtService jwtService,
            UserService userService,
            RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    record LoginRequest(String email, String password) {
    }

    record AuthResponse(String accessToken, String refreshToken) {
    }

    record RefreshRequest(String refreshToken) {
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.email());

        if (user == null || !user.isEnabled()
                || !user.getPassword().equals(request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshTokenString = jwtService.generateRefreshToken(user);

        refreshTokenService.create(
                user.getIdUser(),
                JwtProperties.timeRefreshMs,
                refreshTokenString);

        return ResponseEntity.ok(
                new AuthResponse(accessToken, refreshTokenString));
    }

    @PostMapping("/refresh")
    @Transactional
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshToken refreshToken = refreshTokenService.findByToken(request.refreshToken());

        if (refreshToken == null) {
            System.out.println("→ Token NO encontrado en BD");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!refreshTokenService.isValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findById(refreshToken.getIdUser());

        String newAccessToken = jwtService.generateAccessToken(user);

        refreshTokenService.deleteByToken(refreshToken.getToken());

        RefreshToken newRefreshToken = refreshTokenService.create(
                user.getIdUser(),
                JwtProperties.timeRefreshMs,
                jwtService.generateRefreshToken(user));

        return ResponseEntity.ok(
                new AuthResponse(newAccessToken, newRefreshToken.getToken()));
    }
}
