import { Component } from '@angular/core';
import { PersonGetAll } from './person-get-all/person-get-all';
import { PersonInsert } from './person-insert/person-insert';
import { PersonGetbydni } from './person-get-by-dni/person-get-by-dni';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [
    PersonGetAll,
    PersonInsert,
    PersonGetbydni
  ],
  templateUrl: './person.html',
  styleUrl: './person.css',
})
export class Person {

}
