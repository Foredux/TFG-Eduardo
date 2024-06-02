import { Component } from '@angular/core';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { CrearSalidaResponse } from '../../../../modelos/socios/crear-salida';

@Component({
  selector: 'app-page-crear-salida',
  templateUrl: './page-crear-salida.component.html',
  styleUrl: './page-crear-salida.component.css'
})
export class PageCrearSalidaComponent {
  idBarco!:string;
  constructor(private socioService:SocioService,private router:Router,private route: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.idBarco = params['idBarco'];
    });
  }

  profileLogin = new FormGroup({
    destino: new FormControl(''), 
    nombrePatron: new FormControl('') 
  })

  login() {
    console.log('Datos enviados al servidor:', this.profileLogin.value); 
  
    this.socioService.crearSalida(this.idBarco!,this.profileLogin.value.destino!, this.profileLogin.value.nombrePatron!)
      .subscribe((l: CrearSalidaResponse) => {
        
        
        this.router.navigate(['/crear-salida']);
       

      });
  }
}
