import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuardService implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    let token = localStorage.getItem('TOKEN');
    let tokenSocio = localStorage.getItem('TOKEN_SOCIO');
    if (token || tokenSocio) {
      // Si el token existe, permite la navegación
      return true;
    } else {
      // Si el token no existe, redirige al login y cancela la navegación
      this.router.navigate(['/login']);
      return false;
    }
  }
}
