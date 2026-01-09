import { Component } from '@angular/core';
// import { RouterOutlet } from '@angular/router';
import { Calculator } from './page/calculator/calculator';
import { PersonInsert } from './page/person/person-insert/person-insert';

@Component({
	selector: 'app-root',
	imports: [
		// RouterOutlet,
		Calculator,
		PersonInsert
	],
	templateUrl: './app.html',
	styleUrl: './app.css'
})

export class App {
	constructor() {}
}