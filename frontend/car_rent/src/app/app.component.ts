import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fas} from "@fortawesome/free-solid-svg-icons";
import {SidebarComponent} from "./components/sidebar/sidebar.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {NewUserComponent} from "./components/user/new-user/new-user.component";

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [
        RouterOutlet,
        FaIconComponent,
        SidebarComponent,
        DashboardComponent,
        NewUserComponent
    ],
    templateUrl: './app.component.html',
    styleUrl: './app.component.sass'
})
export class AppComponent {

    constructor(library: FaIconLibrary) {
        library.addIconPacks(fas);
    }
}
