import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Persons } from '../models/user/person';
import { tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class PersonService {

    private apiInsert = 'person/insert';
    private apiGetAll = 'person/getall';
    private apiGetByDni = 'person/getbydni';

    // 🔥 Estado real compartido
    private persons$ = new BehaviorSubject<Persons[]>([]);

    constructor(private http: HttpClient) { }

    /** Exponer listado reactivo */
    getPersons(): Observable<Persons[]> {
        return this.persons$.asObservable();
    }

    /** Carga inicial / recarga */
    loadPersons(): void {
        this.http
            .get<Persons[]>(this.apiGetAll)
            .subscribe(res => this.persons$.next(res));
    }

    /** Insertar persona */
    insert(person: Persons): Observable<Persons> {
        return this.http
            .post<Persons>(this.apiInsert, person)
            .pipe(
                tap(() => {
                    // 👉 si llegó aquí, el insert fue exitoso
                    this.loadPersons();
                })
            );
    }

    /** Buscar por DNI */
    getByDni(dni: string): Observable<Persons> {
        return this.http.get<Persons>(`${this.apiGetByDni}/${dni}`);
    }
}
