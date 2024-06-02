import { Component, OnInit } from '@angular/core';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { SocioLogueadoResponse } from '../../../../modelos/socios/socio-logueado';

@Component({
  selector: 'app-page-ver-perfil',
  templateUrl: './page-ver-perfil.component.html',
  styleUrl: './page-ver-perfil.component.css'
})
export class PageVerPerfilComponent implements OnInit{

  constructor(private socioService:SocioService){}
  socioLogueado!:SocioLogueadoResponse;
  ngOnInit(): void {
    this.socioService.socioLogueado().subscribe(s=>{
      this.socioLogueado = s;
    })
  }

}
