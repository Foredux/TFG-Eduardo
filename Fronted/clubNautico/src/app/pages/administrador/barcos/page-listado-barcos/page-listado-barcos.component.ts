import { Component, OnInit } from '@angular/core';
import { ListadoBarcosAdmin } from '../../../../modelos/admin/listado-barcos-admin';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';
import { ListadoBarcosDisponibles } from '../../../../modelos/admin/listado-barcos-disponibles';
import { Router } from '@angular/router';
import { MotorBusqueda } from '../../../../modelos/admin/motor-busqueda';

@Component({
  selector: 'app-page-listado-barcos',
  templateUrl: './page-listado-barcos.component.html',
  styleUrl: './page-listado-barcos.component.css'
})
export class PageListadoBarcosComponent implements OnInit{

  barcosList: ListadoBarcosAdmin [] = [];
  barcosListDisponible: ListadoBarcosDisponibles [] = [];
  barcosListOcupados: ListadoBarcosDisponibles [] = [];
  seleccionarOpcion!: string;
  busqueda:string = '';
  barcoBusqueda: MotorBusqueda [] = [];
  
  constructor(private adminService:AdministradorService,private router:Router){}
  ngOnInit(): void {
    this.adminService.listarBarcos().subscribe(b=>{
      this.barcosList= b;
    })
    this.adminService.listarBracosDisponibles().subscribe(b=>{
      this.barcosListDisponible = b;
    })
    this.adminService.listadoBarcosOcupados().subscribe(b=>{
      this.barcosListOcupados = b;
    })

  }
  editarBarco(id: string) {
    
    this.router.navigate(['/editar-barco', id]);
}
eliminarBarco(id:string){
  this.adminService.deleteBarco(id).subscribe(b=>{
    location.reload();
  })
}

motorBusqueda(busqueda:string){
  this.adminService.motorBusqueda(busqueda).subscribe(b=>{
    this.barcoBusqueda = b;
  })
}
cambioOpcion() {
  if (this.seleccionarOpcion !== 'busqueda') {
    this.barcoBusqueda = [];
  }
}


 
get selectedBarcosList() {
  if (this.barcoBusqueda.length > 0) {
    
    return this.barcoBusqueda;
    
  }
  switch (this.seleccionarOpcion) {
    
    case 'todos':
      

      return this.barcosList;
    case 'disponibles':
      return this.barcosListDisponible;
    case 'ocupados':
      return this.barcosListOcupados;
    default:
      return [];
  }
}


}
