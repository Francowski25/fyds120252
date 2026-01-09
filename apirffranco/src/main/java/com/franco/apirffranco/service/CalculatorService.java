package com.franco.apirffranco.service;


import org.springframework.stereotype.Service;

import com.franco.apirffranco.model.NumerosDTO;

@Service
public class CalculatorService {

    public int sumar(NumerosDTO number) {
        return number.getNumberOne() + number.getNumberTwo();
    }

    public int restar(NumerosDTO number){
        return number.getNumberOne() - number.getNumberTwo();
    }

    public int multiplicar(NumerosDTO number){
        return number.getNumberOne() * number.getNumberTwo();
    }
}