import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PersonService } from '../../../services/person.service';

@Component({
  selector: 'app-person-get-by-dni',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './person-get-by-dni.html',
  styleUrl: './person-get-by-dni.css',
})

export class PersonGetbydni {

  dni: string = '';
  person: any = null;
  loading = false;
  errorMsg = '';

  constructor(private personService: PersonService) { }

  buscarPorDni(): void {
    if (!this.dni.trim()) return;

    this.loading = true;
    this.errorMsg = '';
    this.person = null;

    this.personService.getByDni(this.dni).subscribe({
      next: (data) => {
        this.person = data;
        this.loading = false;
      },
      error: () => {
        this.errorMsg = 'No se encontró ninguna persona con ese DNI.';
        this.loading = false;
      }
    });
  }

}
