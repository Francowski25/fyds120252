import { Routes } from '@angular/router';
import { Calculator } from './page/calculator/calculator';
import { Home } from './page/main/home/home';
import { Note } from './page/note/note';
import { Product } from './page/product/product';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { authGuard } from '../app/guards/auth';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';
import { inject } from '@angular/core';

export const routes: Routes = [
  {
    path: 'login',
    component: Login,
    canActivate: [() => {
      if (inject(AuthService).isLoggedIn()) {
        inject(Router).navigate(['/home']);
        return false;
      }
      return true;
    }],
    title: 'Iniciar Sesión'
  },
  {
    path: 'register',
    component: Register,
    canActivate: [() => {
      if (inject(AuthService).isLoggedIn()) {
        inject(Router).navigate(['/home']);
        return false;
      }
      return true;
    }],
    title: 'Registro'
  },

  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },

  {
    path: '',
    canActivate: [authGuard],
    children: [
      { path: 'home', component: Home, title: 'Inicio' },
      { path: 'product', component: Product, title: 'Productos' },
      { path: 'calculator', component: Calculator, title: 'Calculadora' },
      { path: 'note', component: Note, title: 'Notas' },

      { path: '', redirectTo: 'home', pathMatch: 'full' }
    ]
  },
  {
    path: '**',
    redirectTo: '/login'
  }
];