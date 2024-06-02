import { Component } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Component({
  selector: 'app-auth-guard',
  templateUrl: './auth-guard.component.html',
  styleUrl: './auth-guard.component.css'
})
export class AuthGuardComponent implements CanActivate{

  constructor(private router: Router) {}

  canActivate(): boolean {
    let token = localStorage.getItem('TOKEN');

    if (token) {
      // Si el token existe, permite la navegación
      return true;
    } else {
      // Si el token no existe, redirige al login y cancela la navegación
      this.router.navigate(['/login']);
      return false;
    }
  }

}
