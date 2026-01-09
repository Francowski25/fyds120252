import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonService } from '../../../services/person.service';
import { Persons } from '../../../models/person/person';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person-insert',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './person-insert.html',
  styleUrls: ['./person-insert.css']
})
export class PersonInsert {

  person: Persons = {
    firstName: '',
    surName: '',
    age: 0,
    createdAt: ''
  };

  success = '';
  error = '';

  constructor(private personService: PersonService) {}

  insert() {
    this.success = '';
    this.error = '';

    this.personService.insert(this.person).subscribe({
      next: () => {
        this.success = 'Persona registrada correctamente';

        this.person = {
          firstName: '',
          surName: '',
          age: 0,
          createdAt: ''
        };
      },
      error: () => {
        this.error = 'Error al insertar persona';
      }
    });
  }
}

