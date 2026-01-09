import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Persons } from '../models/person/person';

@Injectable({
    providedIn: 'root'
})
export class PersonService {

    private apiUrl = '/api/person';

    constructor(private http: HttpClient) {}

    insert(person: Persons): Observable<Persons> {
        return this.http.post<Persons>(
            `${this.apiUrl}/insert`,
        person
        );
    }
}
