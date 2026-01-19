package com.franco.apirffranco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franco.apirffranco.dto.DtoUser;
import com.franco.apirffranco.model.User;
import com.franco.apirffranco.service.UserService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<User> insert(@RequestBody DtoUser dtoUser) {
        User user = userService.save(dtoUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/getbydni/{dni}")
    public ResponseEntity<User> getByDni(@PathVariable String dni) {

        User user = userService.findByDni(dni);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
