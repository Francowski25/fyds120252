package com.franco.apirffranco.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tRefreshToken")
public class RefreshToken {

    @Id
    @Column(name = "idRefreshToken")
    private String idRefreshToken;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration")
    private Date expiration;

    @Column(name = "idUser")
    private String idUser;

    @Column(name = "createdAt")
    private Date createdAt;

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

    public Date getCreatedAt() {
        return createdAt;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
