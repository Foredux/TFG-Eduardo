import { Component, OnInit } from '@angular/core';
import { AdminLoguedResponse } from '../../../modelos/admin/admin-logued';
import { AdministradorService } from '../../../servicios/administrador/administrador.service';
import { LogoutAdmin } from '../../../modelos/admin/logout-admin';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  administrador!:AdminLoguedResponse;
  admini!:LogoutAdmin;
  constructor (private admin: AdministradorService){}
  ngOnInit(): void {
    this.admin.getAdminLogued().subscribe(a=>{
      this.administrador= a;
    })
    
  }
  logOut(){
    this.admin.logOutAdmin().subscribe(a=>{
      this.admini = a;
    })
  }

}
