import { Component } from '@angular/core';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';
import { FormControl, FormGroup } from '@angular/forms';
import { AddBarco } from '../../../../modelos/admin/add-barco';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-add-barco',
  templateUrl: './page-add-barco.component.html',
  styleUrl: './page-add-barco.component.css'
})
export class PageAddBarcoComponent {
  constructor(private adminService:AdministradorService,private router:Router){}
  errorMessage: string | null = null; 
  profileLogin = new FormGroup({
    matricula: new FormControl(''), 
    nombre: new FormControl(''),
    numeroAmarre: new FormControl(),
    cuota: new FormControl(),
    
  })

  addBarco() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.adminService.addBarco(this.profileLogin.value.matricula!, this.profileLogin.value.nombre!,this.profileLogin.value.numeroAmarre,this.profileLogin.value.cuota)
    .subscribe({
      next: (l: AddBarco) => {
        this.router.navigate(['/listado-barcos']);
      },
      error: (error) => {
        if (error.status === 400 && error.error === 'Ya hay un barco con esa matricula') {
          this.errorMessage = 'Ya hay un barco con esa matrícula'; 
        }
      }
    });
  }

}
