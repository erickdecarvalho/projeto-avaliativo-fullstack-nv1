import {Component} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {SidebarComponent} from "../sidebar/sidebar.component";
import {ThemeService} from "../../services/theme.service";
import {RouterOutlet} from "@angular/router";

@Component({
    selector: 'dashboard',
    standalone: true,
    imports: [
        FaIconComponent,
        SidebarComponent,
        RouterOutlet
    ],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.sass'
})
export class DashboardComponent {
    constructor(private themeService: ThemeService) {
    }
    isSidebarExpanded: boolean = true;

    toggleSidebar(): void {
        this.isSidebarExpanded = !this.isSidebarExpanded;
    }

    toggleTheme(): void {
        this.themeService.toggleTheme();
    }
}
