import { Component, OnInit } from '@angular/core';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { CrearSalidaResponse } from '../../../../modelos/socios/crear-salida';
import { Barco, BarcosAsociadosResponse, Salida } from '../../../../modelos/socios/ver-barcos-asociados';
import { Router } from '@angular/router';
import { EditarSalida } from '../../../../modelos/socios/editar-salida';
import { DesasignarBarco } from '../../../../modelos/socios/des-asignar-barco';

@Component({
  selector: 'app-page-salida',
  templateUrl: './page-salida.component.html',
  styleUrl: './page-salida.component.css'
})
export class PageSalidaComponent implements OnInit{

  socio!:CrearSalidaResponse;
  barcosAsociados:Barco [] = [];
  salidas: Salida [] = [];
  finalizarSaldia!:EditarSalida;
  desasiganrBarco!:DesasignarBarco;
  constructor(private socioService:SocioService,private router:Router){}

  ngOnInit(): void {
    this.socioService.verBarcosAsociados().subscribe(s=>{
      this.barcosAsociados = s.barcos;
    })

    
  }

  crearSalida(id:string){
    this.router.navigate(['/crear-salida', id]);
  }

  editarSalida(id:string){
    this.socioService.finalizarSalida(id).subscribe(s=>{
      this.finalizarSaldia = s;
      location.reload();
    })
  }

  desasignarBarco(id:string){
    this.socioService.desasignarBarco(id).subscribe(s=>{
      this.desasiganrBarco = s;
      location.reload()
    })
  }

  eliminarSalida(id: string) {
    if (confirm('¿Estás seguro de que quieres eliminar esta salida?')) {
      this.socioService.eliminarSalida(id).subscribe(() => {
        // Filtrar la salida eliminada de la lista de salidas
        this.salidas = this.salidas.filter(salida => salida.id !== id);
        location.reload();
      });
    }
  }

}
