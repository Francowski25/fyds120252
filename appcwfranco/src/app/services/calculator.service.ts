import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CalculatorService {

    private apiUrl = '/api/suma';

    constructor(private http: HttpClient) {}

    sumar(numeros: { numberOne: number; numberTwo: number }): Observable<number> {
        return this.http.post<number>(this.apiUrl, numeros);
    }
}
