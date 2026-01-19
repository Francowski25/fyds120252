package com.franco.apirffranco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franco.apirffranco.model.Calculator;
import com.franco.apirffranco.service.CalculatorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/suma")
    public ResponseEntity<Integer> sumar(@RequestBody Calculator numeros) {
        int resultado = calculatorService.sumar(numeros);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/resta")
    public ResponseEntity<Integer> resta(@RequestBody Calculator numeros) {
        int resultado = calculatorService.restar(numeros);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/multiplicacion")
    public ResponseEntity<Integer> multi(@RequestBody Calculator numeros) {
        int resultado = calculatorService.multiplicar(numeros);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/division")
    public ResponseEntity<Integer> division(@RequestBody Calculator numeros) {
        int resultado = calculatorService.dividir(numeros);
        return ResponseEntity.ok(resultado);
    }
}