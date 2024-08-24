import { Component } from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'show-user',
  standalone: true,
  imports: [
    FaIconComponent
  ],
  templateUrl: './show-user.component.html',
  styleUrl: './show-user.component.sass'
})
export class ShowUserComponent {

}
