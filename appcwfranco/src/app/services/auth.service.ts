import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiLogin = 'auth/login';
    private apiRegister = 'auth/register';
    private apiRefresh = 'auth/refresh';

    constructor(
        private http: HttpClient,
        private router: Router
    ) { }

    register(credentials: { email: string; password: string }): Observable<{ accessToken: string; refreshToken: string }> {
        return this.http.post<{ accessToken: string; refreshToken: string }>(this.apiRegister, credentials).pipe(
            tap(response => {
                this.saveTokens(response.accessToken, response.refreshToken);
            }),
            catchError(error => throwError(() => error))
        );
    }

    login(credentials: { email: string; password: string }): Observable<{ accessToken: string; refreshToken: string }> {
        return this.http.post<{ accessToken: string; refreshToken: string }>(this.apiLogin, credentials).pipe(
            tap(response => {
                this.saveTokens(response.accessToken, response.refreshToken);
            }),
            catchError(error => throwError(() => error))
        );
    }

    saveTokens(accessToken: string, refreshToken: string): void {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
    }

    isLoggedIn(): boolean {
        return !!localStorage.getItem('accessToken');
    }

    getAccessToken(): string | null {
        return localStorage.getItem('accessToken');
    }

    getRefreshToken(): string | null {
        return localStorage.getItem('refreshToken');
    }

    logout(): void {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        this.router.navigate(['/login']);
    }

    refreshToken(): Observable<{ accessToken: string; refreshToken: string }> {
        const refreshToken = this.getRefreshToken();
        if (!refreshToken) {
            return throwError(() => new Error('No refresh token available'));
        }

        return this.http.post<{ accessToken: string; refreshToken: string }>('/auth/refresh', { refreshToken }).pipe(
            tap(response => {
                this.saveTokens(response.accessToken, response.refreshToken);
            })
        );
    }
}