import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CalculatorService {

    private apiSuma = 'calculator/suma';
    private apiResta = 'calculator/resta';
    private apiMulpi = 'calculator/multiplicacion';
    private apiDivision = 'calculator/division';

    constructor(private http: HttpClient) { }

    sumar(numeros: { numberOne: number; numberTwo: number }): Observable<number> {
        return this.http.post<number>(this.apiSuma, numeros);
    }

    restar(numeros: { numberOne: number; numberTwo: number }): Observable<number> {
        return this.http.post<number>(this.apiResta, numeros);
    }

    multiplicar(numeros: { numberOne: number; numberTwo: number }): Observable<number> {
        return this.http.post<number>(this.apiMulpi, numeros);
    }

    dividir(numeros: { numberOne: number; numberTwo: number }): Observable<number> {
        return this.http.post<number>(this.apiDivision, numeros);
    }
}