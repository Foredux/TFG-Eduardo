import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { LoginServiceService } from '../../../servicios/login/login-service.service';
import { LoginAdminResponse } from '../../../modelos/admin/login-admin-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent {
  loading = false; // Estado de carga

  constructor(private loginService: LoginServiceService, private router: Router) {}

  profileLogin = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  login() {
    if (this.profileLogin.invalid) {
      return;
    }

    this.loading = true; // Iniciar carga
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.loginService.loginAdmin(this.profileLogin.value.email!, this.profileLogin.value.password!)
      .subscribe(
        (l: LoginAdminResponse) => {
          this.loading = false; // Finalizar carga
          localStorage.setItem('TOKEN', l.token);
          localStorage.setItem('USER_ID', l.id);
          this.router.navigate(['/home-page-admin']);
        },
        (error) => {
          this.loading = false; // Finalizar carga
          console.error('Error during login', error);
        }
      );
  }
}

