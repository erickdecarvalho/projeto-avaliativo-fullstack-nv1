import { Component } from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'rent-header',
  standalone: true,
  imports: [
    FaIconComponent
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.sass'
})
export class HeaderComponent {

}
