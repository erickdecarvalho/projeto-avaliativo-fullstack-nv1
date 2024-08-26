import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fas} from "@fortawesome/free-solid-svg-icons";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {NewUserComponent} from "./components/user/new-user/new-user.component";
import {ThemeService} from "./services/theme.service";
import {HeaderComponent} from "./components/header/header.component";
import {LoginComponent} from "./components/login/login.component";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'root',
    standalone: true,
    imports: [
        DashboardComponent,
        FaIconComponent,
        HeaderComponent,
        LoginComponent,
        NavbarComponent,
        NewUserComponent,
        FormsModule,
        RouterOutlet
    ],
    templateUrl: './app.component.html',
    styleUrl: './app.component.sass'
})
export class AppComponent {

    isNavbarExpanded: boolean = true;

    constructor(library: FaIconLibrary, private themeService: ThemeService) {
        library.addIconPacks(fas);
    }

    toggleNavbar(): void {
        this.isNavbarExpanded = !this.isNavbarExpanded;
    }

    toggleTheme(): void {
        this.themeService.toggleTheme();
    }
}
