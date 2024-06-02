import { Component } from '@angular/core';
import { SocioService } from '../../../servicios/socios/socio.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginResponseSocio } from '../../../modelos/socios/login-socio';

@Component({
  selector: 'app-page-login-socio',
  templateUrl: './page-login-socio.component.html',
  styleUrls: ['./page-login-socio.component.css']
})
export class PageLoginSocioComponent {
  loading = false; // Variable para controlar el estado de carga

  constructor(private socioService: SocioService, private router: Router) {}

  profileLogin = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  login() {
    // Se activa la carga
    this.loading = true;
    console.log('Datos enviados al servidor:', this.profileLogin.value);

    this.socioService.loginSocio(this.profileLogin.value.email!, this.profileLogin.value.password!)
      .subscribe((l: LoginResponseSocio) => {
        localStorage.setItem('TOKEN_SOCIO', l.token);
        localStorage.setItem('USER_ID_SOCIO', l.id);
        this.router.navigate(['/home-page-socio']);
      })
      .add(() => {
        // Se desactiva la carga cuando la solicitud ha finalizado (ya sea éxito o fracaso)
        this.loading = false;
      });
  }
}
