import { Component, OnInit } from '@angular/core';
import { ListadoSocios } from '../../../../modelos/admin/list-socios';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';

@Component({
  selector: 'app-page-list-socios',
  templateUrl: './page-list-socios.component.html',
  styleUrl: './page-list-socios.component.css'
})
export class PageListSociosComponent implements OnInit{


  listadoSocios: ListadoSocios [] = [];

  constructor(private adminService:AdministradorService){}
  ngOnInit(): void {
    this.adminService.listadoSocios().subscribe(s=>{
      this.listadoSocios = s;
    })
  }

  eliminarSocio(id:string){
    this.adminService.bannearSocio(id).subscribe(s=>{
      location.reload();
    })
  }

 
}


