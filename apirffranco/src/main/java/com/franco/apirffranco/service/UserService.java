package com.franco.apirffranco.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.apirffranco.dto.DtoUser;
import com.franco.apirffranco.model.User;
import com.franco.apirffranco.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(DtoUser dtoUser) {

        User user = new User();
        user.setIdUser(UUID.randomUUID().toString());
        user.setFirstName(dtoUser.getFirstName());
        user.setSurName(dtoUser.getSurName());
        user.setDni(dtoUser.getDni());
        user.setEmail(dtoUser.getEmail());
        user.setPassword(dtoUser.getPassword());
        user.setRole(dtoUser.getRole());
        user.setEnabled(true);
        user.setCreatedAt(new Date());

        return userRepository.save(user);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findByDni(String dni) {
        return userRepository.findByDni(dni)
                .orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public User findById(String idUser) {
        return userRepository.findById(idUser)
                .orElse(null);
    }

    public void disableUser(String idUser) {
        User user = findById(idUser);
        if (user != null) {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }
}
