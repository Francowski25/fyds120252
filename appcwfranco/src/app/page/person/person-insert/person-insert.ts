import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonService } from '../../../services/person.service';
import { Persons } from '../../../models/user/person';
import { FormsModule } from '@angular/forms';
import { Message } from '../../../models/message/message';

@Component({
  selector: 'app-person-insert',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './person-insert.html',
  styleUrls: ['./person-insert.css']
})
export class PersonInsert {

  person: Persons = { firstName: '', surName: '', dni: '' };

  message: Message | null = null;

  constructor(private personService: PersonService) { }

  insert() {

    // 🔒 Validaciones frontend
    if (!this.person.firstName || !this.person.surName || !this.person.dni) {
      this.message = { type: 'error', text: 'Todos los campos son obligatorios' };
      return;
    }

    if (!/^\d{8}$/.test(this.person.dni)) {
      this.message = { type: 'error', text: 'El DNI debe tener 8 dígitos' };
      return;
    }

    this.message = null;

    this.personService.insert(this.person).subscribe({
      next: () => {
        this.message = { type: 'success', text: 'Persona registrada correctamente' };
        this.person = { firstName: '', surName: '', dni: '' };
      },
      error: () => {
        this.message = { type: 'error', text: 'Error del servidor' };
      }
    });


  }

}

