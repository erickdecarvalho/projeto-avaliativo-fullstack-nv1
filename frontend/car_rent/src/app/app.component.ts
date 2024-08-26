import {Component} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fas} from "@fortawesome/free-solid-svg-icons";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {NewUserComponent} from "./components/user/new-user/new-user.component";
import {HeaderComponent} from "./components/header/header.component";
import {LoginComponent} from "./components/login/login.component";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";

@Component({
    selector: 'rent-root',
    standalone: true,
    imports: [
        DashboardComponent,
        FaIconComponent,
        HeaderComponent,
        LoginComponent,
        NavbarComponent,
        NewUserComponent,
        FormsModule,
        RouterOutlet,
        NgIf
    ],
    templateUrl: './app.component.html'
})
export class AppComponent {
    showHeader: boolean = true;

    constructor(library: FaIconLibrary, private router: Router) {
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.showHeader = !event.url.includes('/login');
            }
        });
        library.addIconPacks(fas);
    }
}