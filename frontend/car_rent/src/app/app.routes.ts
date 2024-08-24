import { Routes } from '@angular/router';
import {NewUserComponent} from "./components/user/new-user/new-user.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {ShowUserComponent} from "./components/user/show-user/show-user.component";

export const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'user/new-user', component: NewUserComponent },
    { path: 'user/show-user', component: ShowUserComponent },
];
