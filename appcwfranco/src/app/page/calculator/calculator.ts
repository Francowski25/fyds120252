import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalculatorService } from '../../services/calculator.service';

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './calculator.html',
  styleUrl: './calculator.css',
})

export class Calculator {
  numberOne = 0;
  numberTwo = 0;

  resultado: number | null = null;
  error = '';

  constructor(private calculatorService: CalculatorService) {}

  calcular() {
    this.error = '';

    this.calculatorService.sumar({
      numberOne: this.numberOne,
      numberTwo: this.numberTwo
    }).subscribe({
      next: res => this.resultado = res,
      error: () => this.error = 'Error al conectar con el servidor'
    });
  }
}
