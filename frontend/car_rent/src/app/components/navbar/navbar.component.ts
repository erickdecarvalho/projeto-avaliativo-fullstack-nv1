import {Component} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {CommonModule, NgClass, NgOptimizedImage} from "@angular/common";
import {NavigationEnd, Router} from "@angular/router";
import {filter} from "rxjs";

@Component({
    selector: 'rent-navbar',
    standalone: true,
    imports: [
        CommonModule,
        FaIconComponent,
        NgClass,
        NgOptimizedImage
    ],
    templateUrl: './navbar.component.html'
})
export class NavbarComponent {
    currentRoute: string = '';
    activeClass: string = "text-indigo-400 border-b-2 border-indigo-700 transform transition duration-500 ease-in-out";
    inactiveClass: string = "text-indigo-900 hover:text-indigo-600 hover:border-b-2 hover:border-indigo-900 hover:transform transition duration-700";

    constructor(private router: Router) {
    }

    ngOnInit(): void {
        this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
            this.currentRoute = event.urlAfterRedirects;
        });
    }

    isActive(route: string): string {
        return this.currentRoute === route || (this.currentRoute === '/dashboard' && route === '/dashboard')
            ? this.activeClass
            : this.inactiveClass;
    }
}