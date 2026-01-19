package com.franco.apirffranco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franco.apirffranco.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByDni(String dni);

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);
}