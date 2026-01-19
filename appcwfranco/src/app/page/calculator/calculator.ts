import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalculatorService } from '../../services/calculator.service';

type Operation = 'sumar' | 'restar' | 'multiplicar' | 'dividir';

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './calculator.html',
  styleUrl: './calculator.css',
})
export class Calculator {
  numberOne: number = 0;
  numberTwo: number = 0;

  resultado: number | null = null;
  error: string = '';
  loading: boolean = false;

  constructor(private calculatorService: CalculatorService) { }

  calcular(operation: Operation): void {
    this.resetState();

    if (operation === 'dividir' && this.numberTwo === 0) {
      this.error = 'No se puede dividir entre cero';
      return;
    }

    this.loading = true;

    this.calculatorService[operation]({
      numberOne: this.numberOne,
      numberTwo: this.numberTwo,
    }).subscribe({
      next: (res) => this.resultado = res,
      error: () => this.error = 'Error al conectar con el servidor',
      complete: () => this.loading = false,
    });
  }

  private resetState(): void {
    this.error = '';
    this.resultado = null;
  }
}
