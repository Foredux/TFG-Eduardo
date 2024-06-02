import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddBarco } from '../../../../modelos/admin/add-barco';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';
import { EditarBarco } from '../../../../modelos/admin/editar-barco';

@Component({
  selector: 'app-page-editar-barco',
  templateUrl: './page-editar-barco.component.html',
  styleUrl: './page-editar-barco.component.css'
})
export class PageEditarBarcoComponent {
  idBarco!:string;
  errorMessage: string | null = null; 

  constructor(private adminService:AdministradorService,private router:Router, private route: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.idBarco = params['id'];
    });
  }
  profileLogin = new FormGroup({
    matricula: new FormControl(''), 
    nombre: new FormControl(''),
    numeroAmarre: new FormControl(),
    cuota: new FormControl(),
    
  })

  EditarBarco() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.adminService.editarBarco(this.idBarco!,this.profileLogin.value.matricula!, this.profileLogin.value.nombre!,this.profileLogin.value.numeroAmarre,this.profileLogin.value.cuota)
      .subscribe({
        next: (l: EditarBarco) => {
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
