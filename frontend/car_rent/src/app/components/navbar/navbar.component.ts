import {Component, Input} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgClass} from "@angular/common";

@Component({
  selector: 'rent-navbar',
  standalone: true,
  imports: [
    FaIconComponent,
    NgClass
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.sass'
})
export class NavbarComponent {
  @Input() isSidebarExpanded: boolean = true;
}
