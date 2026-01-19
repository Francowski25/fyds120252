import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './partial/header/header';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';

@Component({
	selector: 'app-root',
	standalone: true,
	imports: [
		RouterOutlet,
		Header,
		CommonModule
	],
	templateUrl: './app.html',
	styleUrl: './app.css'
})

export class App {
	constructor(public authService: AuthService) { }
}