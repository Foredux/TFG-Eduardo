import { Component, OnInit } from '@angular/core';
import { EditarPerfilAdmin } from '../../../../modelos/admin/editar-perfil-admin';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';
import { AdminLoguedResponse } from '../../../../modelos/admin/admin-logued';

@Component({
  selector: 'app-page-perfil-admin',
  templateUrl: './page-perfil-admin.component.html',
  styleUrl: './page-perfil-admin.component.css'
})
export class PagePerfilAdminComponent implements OnInit {

  admin!:AdminLoguedResponse;

  constructor(private adminService:AdministradorService){}


  ngOnInit(): void {
    this.adminService.getAdminLogued().subscribe(a=>{
      this.admin = a;
    })
  }

  

}
