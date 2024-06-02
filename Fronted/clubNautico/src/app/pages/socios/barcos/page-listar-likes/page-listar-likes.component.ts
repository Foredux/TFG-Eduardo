import { Component, OnInit } from '@angular/core';
import { SocioService } from '../../../../servicios/socios/socio.service';
import { VerLikesResponse } from '../../../../modelos/socios/ver-like-response';
import { AsignarBarcosResponse } from '../../../../modelos/socios/asignar-barco-response';

@Component({
  selector: 'app-page-listar-likes',
  templateUrl: './page-listar-likes.component.html',
  styleUrl: './page-listar-likes.component.css'
})
export class PageListarLikesComponent implements OnInit{

constructor(private socioService:SocioService){}

  verLikes: VerLikesResponse [] = [];
  asiganrBarco!: AsignarBarcosResponse;
  errorMessage: string | null = null;
  ngOnInit(): void {
    this.socioService.verLikes().subscribe(s=>{
      this.verLikes = s;
    })
  }

  quitarLike(id:string){
    this.socioService.quitarLike(id).subscribe(s=>{
      location.reload();
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

}
