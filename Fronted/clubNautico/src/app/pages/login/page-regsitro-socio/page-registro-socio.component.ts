import { Component } from '@angular/core';
import { SocioService } from '../../../servicios/socios/socio.service';
import { FormControl, FormGroup } from '@angular/forms';
import { RegistroResponseSocio } from '../../../modelos/socios/registro-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-login-socio',
  templateUrl: './page-login-socio.component.html',
  styleUrl: './page-login-socio.component.css',
})
export class PageRegistroSocioComponent {
  errorMessage: string | null = null;
  constructor(private socioService: SocioService, private router: Router) {}
  profileLogin = new FormGroup({
    email: new FormControl(''),
    fotoUrl: new FormControl(''),
    name: new FormControl(''),
    password: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    phoneNumber: new FormControl(''),
  });

  register() {
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.socioService
      .registro(
        this.profileLogin.value.email!,
        this.profileLogin.value.name!,
        this.profileLogin.value.username!,
        this.profileLogin.value.lastName!,
        this.profileLogin.value.password!,
        this.profileLogin.value.phoneNumber!,
        this.profileLogin.value.fotoUrl!
      )
      .subscribe({
        next: (l: RegistroResponseSocio) => {
          this.router.navigate(['/confirmar-email']);
        },
        error: (error) => {
          if (
            error.status === 400 &&
            error.error === 'El email ya ha sido registrado'
          ) {
            this.errorMessage = 'El email ya ha sido registrado';
          }
        },
      });
  }
}
