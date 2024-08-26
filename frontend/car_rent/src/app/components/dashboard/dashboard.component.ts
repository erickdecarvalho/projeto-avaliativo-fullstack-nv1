import {Component} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NavbarComponent} from "../navbar/navbar.component";
import {ThemeService} from "../../services/theme.service";
import {RouterOutlet} from "@angular/router";
import {HeaderComponent} from "../header/header.component";

@Component({
    selector: 'rent-dashboard',
    standalone: true,
    imports: [
        FaIconComponent,
        HeaderComponent,
        NavbarComponent,
        RouterOutlet
    ],
    templateUrl: './dashboard.component.html'
})
export class DashboardComponent {
    constructor(private themeService: ThemeService) {
    }
    isSidebarExpanded: boolean = false;

    toggleSidebar(): void {
        this.isSidebarExpanded = !this.isSidebarExpanded;
    }

    toggleTheme(): void {
        this.themeService.toggleTheme();
    }
}
