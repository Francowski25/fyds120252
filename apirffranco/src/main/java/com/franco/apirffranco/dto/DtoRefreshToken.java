package com.franco.apirffranco.dto;

import java.util.Date;

public class DtoRefreshToken {

    private String idRefreshToken;
    private String token;
    private Date expiration;
    private String idUser;

    public String getIdRefreshToken() {
        return idRefreshToken;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdRefreshToken(String idRefreshToken) {
        this.idRefreshToken = idRefreshToken;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
