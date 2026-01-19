import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../../services/person.service';
import { Persons } from '../../../models/user/person';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-person-get-all',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './person-get-all.html',
  styleUrl: './person-get-all.css',
})
export class PersonGetAll implements OnInit {

  persons: Persons[] = [];
  loading = true;

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.personService.getPersons().subscribe(res => {
      this.persons = res;
      this.loading = false;
    });

    // carga inicial
    this.personService.loadPersons();
  }
}
