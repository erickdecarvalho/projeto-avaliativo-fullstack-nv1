import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'rent-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.sass'
})
export class LoginComponent {
  email: string = 'edward.elric@mail.com';
  password: string = '#fullMetal_@lchmist!#';

  constructor(private router: Router) {}

  login(): void {
    console.log('Login:', this.email, this.password);
    this.router.navigate(['/dashboard']);
  }
}
