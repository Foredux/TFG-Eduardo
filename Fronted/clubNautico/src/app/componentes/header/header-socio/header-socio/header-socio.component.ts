import { Component, OnInit } from '@angular/core';
import { SocioLogueadoResponse } from '../../../../modelos/socios/socio-logueado';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { LogoutSocioResponse } from '../../../../modelos/socios/logout-socio';

@Component({
  selector: 'app-header-socio',
  templateUrl: './header-socio.component.html',
  styleUrl: './header-socio.component.css'
})
export class HeaderSocioComponent implements OnInit {
  socioLogued!:SocioLogueadoResponse;
  socioLogout!:LogoutSocioResponse;
  //admini!:LogoutAdmin;
  constructor (private socioService: SocioService){}
  ngOnInit(): void {
    this.socioService.socioLogueado().subscribe(a=>{
      this.socioLogued= a;
    })
    
  }
  logOut(){
    this.socioService.socioLogout().subscribe(a=>{
      this.socioLogout = a;
    })
  }
}
