import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {
  user = {
    firstName: '',
    surName: '',
    dni: '',
    email: '',
    password: '',
    confirmPassword: ''
  };

  errorMessage = '';
  successMessage = '';
  isLoading = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) { }

  onSubmit(form: NgForm) {
    if (form.invalid) {
      Object.keys(form.controls).forEach(key => form.controls[key].markAsTouched());
      return;
    }

    if (this.user.password !== this.user.confirmPassword) {
      this.errorMessage = 'Las contraseñas no coinciden';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    const registerData = {
      firstName: this.user.firstName,
      surName: this.user.surName,
      dni: this.user.dni,
      email: this.user.email,
      password: this.user.password,
      role: 'USER'
    };

    this.http.post('/auth/register', registerData).subscribe({
      next: () => {
        this.successMessage = '¡Registro exitoso! Ahora inicia sesión.';
        this.isLoading = false;

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2500);
      },
      error: (err) => {
        this.isLoading = false;

        if (err.status === 400) {
          this.errorMessage = err.error || 'Datos inválidos (email o DNI ya registrados)';
        } else {
          this.errorMessage = 'Error al conectar con el servidor';
        }
      }
    });
  }

  clearMessages() {
    this.errorMessage = '';
    this.successMessage = '';
  }
}