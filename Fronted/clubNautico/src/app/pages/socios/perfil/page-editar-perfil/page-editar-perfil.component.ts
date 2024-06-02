import { Component, OnInit } from '@angular/core';
import { EditarSocio } from '../../../../modelos/socios/editar-socio';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-page-editar-perfil',
  templateUrl: './page-editar-perfil.component.html',
  styleUrl: './page-editar-perfil.component.css'
})
export class PageEditarPerfilSocioComponent{

  

  constructor(private socioService:SocioService,private router:Router){}
  errorMessage: string | null = null; 
  profileLogin = new FormGroup({
    name: new FormControl(''), 
    lastName: new FormControl(''),
    
    saldo: new FormControl(),
    fotoUrl: new FormControl(''),
    phoneNumber: new FormControl(''),
    username: new FormControl(''),
    
    
  })

  editarPerfil() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.socioService.socioEditar(this.profileLogin.value.name!, this.profileLogin.value.lastName!,this.profileLogin.value.saldo,this.profileLogin.value.fotoUrl!,this.profileLogin.value.phoneNumber!,this.profileLogin.value.username!)
    .subscribe({
      next: (l: EditarSocio) => {
        
      },
      error: (error) => {
        if (error.status === 400 && error.error === 'Ya hay un barco con esa matricula') {
          this.errorMessage = 'Ya hay un barco con esa matrícula'; 
        }
      }
    });
  }

}
