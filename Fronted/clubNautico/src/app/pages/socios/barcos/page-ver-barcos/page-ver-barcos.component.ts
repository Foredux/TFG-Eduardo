import { Component, OnInit } from '@angular/core';
import { ListadoBarcoDisponible } from '../../../../modelos/socios/listar-barcos-disponibles';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { AsignarBarcosResponse } from '../../../../modelos/socios/asignar-barco-response';
import { DarLikeResponse } from '../../../../modelos/socios/dar-like-response';
import { EditarSalida } from '../../../../modelos/socios/editar-salida';

@Component({
  selector: 'app-page-ver-barcos',
  templateUrl: './page-ver-barcos.component.html',
  styleUrl: './page-ver-barcos.component.css'
})
export class PageVerBarcosComponent implements OnInit{

  socio: ListadoBarcoDisponible [] = [];
  errorMessage: string | null = null; 
  asiganrBarco!: AsignarBarcosResponse;
  like!:DarLikeResponse;
  
  constructor(private socioService:SocioService){}
  ngOnInit(): void {
    this.socioService.listarBarcosDisponibles().subscribe(s=>{
      this.socio = s;
    })
   
  }

  asignarBarco(id: string){
    this.socioService.asignarBarco(id).subscribe({
      next: (s: AsignarBarcosResponse)=>{
        this.asiganrBarco = s;
        location.reload();
      },
      error: (error)=>{
        if (error.status === 400 && error.error === 'No tienes suficiente saldo') {
          this.errorMessage = 'No tienes suficiente saldo'; 
        }
      }
     
    })
  }

  
  darLike(id:string){
    this.socioService.darLike(id).subscribe(s=>{
      this.like = s;
      location.reload();
    }
      
    )
  }

  quitarLike(id:string){
    this.socioService.quitarLike(id).subscribe(s=>{
      location.reload();
    })
  }



}
