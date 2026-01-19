package com.franco.apirffranco.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.apirffranco.model.RefreshToken;
import com.franco.apirffranco.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshToken create(String idUser, long expirationMillis, String tokenValue) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setIdRefreshToken(UUID.randomUUID().toString());
        refreshToken.setToken(tokenValue);
        refreshToken.setIdUser(idUser);
        refreshToken.setExpiration(new Date(System.currentTimeMillis() + expirationMillis));
        refreshToken.setCreatedAt(new Date());

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken findByToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);
        return refreshToken.orElse(null);
    }

    public boolean isValid(RefreshToken refreshToken) {
        return refreshToken != null && refreshToken.getExpiration().after(new Date());
    }

    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public void deleteByUser(String idUser) {
        refreshTokenRepository.deleteByIdUser(idUser);
    }
}
