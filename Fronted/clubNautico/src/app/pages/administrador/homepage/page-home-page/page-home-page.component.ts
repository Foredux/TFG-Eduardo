import { Component, OnInit } from '@angular/core';
import { AdminLoguedResponse } from '../../../../modelos/admin/admin-logued';
import { AdministradorService } from '../../../../servicios/administrador/administrador.service';

@Component({
  selector: 'app-page-home-page',
  templateUrl: './page-home-page.component.html',
  styleUrl: './page-home-page.component.css'
})
export class PageHomePageComponent implements OnInit {

  administrador!:AdminLoguedResponse;
  constructor (private admin: AdministradorService){}
  ngOnInit(): void {
    this.admin.getAdminLogued().subscribe(a=>{
      this.administrador= a;
    })
  }

}
