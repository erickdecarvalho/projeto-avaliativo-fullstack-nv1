import {Component, Input} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {CommonModule, NgClass} from "@angular/common";

@Component({
    selector: 'rent-navbar',
    standalone: true,
    imports: [
        CommonModule,
        FaIconComponent,
        NgClass
    ],
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.sass'
})
export class NavbarComponent {
    isOpen: boolean = false;
    currentRoute: string = window.location.pathname;
    activeClass = "bg-gray-600 bg-opacity-25 text-gray-100 border-gray-100";
    inactiveClass = "border-gray-900 text-gray-500 hover:bg-gray-600 hover:bg-opacity-25 hover:text-gray-100";
}
