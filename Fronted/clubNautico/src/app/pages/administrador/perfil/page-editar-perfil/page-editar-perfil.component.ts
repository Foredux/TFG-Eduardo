import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AddBarco } from '../../../../modelos/admin/add-barco';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';
import { EditarPerfilAdmin } from '../../../../modelos/admin/editar-perfil-admin';

@Component({
  selector: 'app-page-editar-perfil',
  templateUrl: './page-editar-perfil.component.html',
  styleUrl: './page-editar-perfil.component.css'
})
export class PageEditarPerfilComponent {
  constructor(private adminService:AdministradorService,private router:Router){}
  errorMessage: string | null = null; 
  profileLogin = new FormGroup({
    name: new FormControl(''), 
    lastName: new FormControl(''),
    fotoUrl: new FormControl(''),
    
    
  })

  editarPerfil() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.adminService.editarPerfil(this.profileLogin.value.name!, this.profileLogin.value.lastName!,this.profileLogin.value.fotoUrl!)
    .subscribe({
      next: (l: EditarPerfilAdmin) => {
        this.router.navigate(['/perfil-admin']);
      },
      error: (error) => {
        if (error.status === 400 && error.error === 'Ya hay un barco con esa matricula') {
          this.errorMessage = 'Ya hay un barco con esa matrícula'; 
        }
      }
    });
  }
}
