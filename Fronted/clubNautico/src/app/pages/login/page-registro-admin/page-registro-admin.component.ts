import { Component } from '@angular/core';
import { AdminLoguedResponse } from '../../../modelos/admin/admin-logued';
import { AdministradorService } from '../../../servicios/administrador/administrador.service';
import { FormGroup, FormControl } from '@angular/forms';
import { LoginAdminResponse } from '../../../modelos/admin/login-admin-response';
import { RegistroAdminResponse } from '../../../modelos/admin/registro-admin';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-registro-admin',
  templateUrl: './page-registro-admin.component.html',
  styleUrl: './page-registro-admin.component.css'
})
export class PageRegistroAdminComponent {
  errorMessage: string | null = null; 
  constructor(private adminService:AdministradorService,private router:Router){}
  profileLogin = new FormGroup({
    email: new FormControl(''), 
    fotoUrl: new FormControl(''),
    name: new FormControl(''),
    lastName: new FormControl(''),
    phoneNumber: new FormControl('')
  })

  register() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.adminService.registroAdmin(this.profileLogin.value.email!, this.profileLogin.value.fotoUrl!,this.profileLogin.value.name!,this.profileLogin.value.lastName!,this.profileLogin.value.phoneNumber!)
      .subscribe( {
        next: (l:RegistroAdminResponse)=>{
          this.router.navigate(['/home-page-admin']);

        },
        error: (error)=>{
          if (error.status === 400 && error.error === 'El email ya ha sido registrado') {
            this.errorMessage = 'El email ya ha sido registrado'; 
          }
        }
       
       

      });
  }
}
