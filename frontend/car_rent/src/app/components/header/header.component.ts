import {Component, HostListener} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";
import {NavbarComponent} from "../navbar/navbar.component";

@Component({
    selector: 'rent-header',
    standalone: true,
    imports: [
        CommonModule,
        FaIconComponent,
        NavbarComponent
    ],
    templateUrl: './header.component.html'
})
export class HeaderComponent {
    dropdownOpen: boolean = false;

    constructor(private router: Router) {
    }

    toggleDropdown(): void {
        this.dropdownOpen = !this.dropdownOpen;
    }

    logout(): void {
        this.router.navigate(['/']).then((): void => this.closeDropdown());
    }

    protected closeDropdown(): void {
        this.dropdownOpen = false;
    }

    @HostListener('document:click', ['$event'])
    onClick(event: Event): void {
        const targetElement = event.target as HTMLElement;
        if (!targetElement.closest('.relative') && this.dropdownOpen) {
            this.closeDropdown();
        }
    }
}