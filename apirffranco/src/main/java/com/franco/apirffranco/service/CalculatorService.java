package com.franco.apirffranco.service;

import org.springframework.stereotype.Service;

import com.franco.apirffranco.model.Calculator;

@Service
public class CalculatorService {

    public int sumar(Calculator number) {
        return number.getNumberOne() + number.getNumberTwo();
    }

    public int restar(Calculator number) {
        return number.getNumberOne() - number.getNumberTwo();
    }

    public int multiplicar(Calculator number) {
        return number.getNumberOne() * number.getNumberTwo();
    }

    public int dividir(Calculator number) {
        return number.getNumberOne() / number.getNumberTwo();
    }
}