import { Component, OnInit } from '@angular/core';
import { SocioLogueadoResponse } from '../../../../modelos/socios/socio-logueado';
import { SocioService } from '../../../../servicios/socios/socio.service';

@Component({
  selector: 'app-home-page-socios',
  templateUrl: './home-page-socios.component.html',
  styleUrl: './home-page-socios.component.css'
})
export class HomePageSociosComponent implements OnInit{

  socio!:SocioLogueadoResponse;
  constructor(private socioService:SocioService){}
  ngOnInit(): void {
    this.socioService.socioLogueado().subscribe(s=>{
      this.socio = s;
    })
  }

}
