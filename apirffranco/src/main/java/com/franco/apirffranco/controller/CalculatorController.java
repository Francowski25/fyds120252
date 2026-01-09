package com.franco.apirffranco.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franco.apirffranco.model.NumerosDTO;
import com.franco.apirffranco.service.CalculatorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/suma")
    public ResponseEntity<Integer> sumar(@RequestBody NumerosDTO numeros) {
        int resultado = calculatorService.sumar(numeros);
        return ResponseEntity.ok(resultado);
    }

}