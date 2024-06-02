import { HttpClient } from '@angular/common/http';
import { ENVIRONMENT_INITIALIZER, Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { AdminLoguedResponse } from '../../modelos/admin/admin-logued';
import { environment } from '../../environments/environment.developments';
import { ListadoBarcosAdmin } from '../../modelos/admin/listado-barcos-admin';
import { ListadoBarcosDisponibles } from '../../modelos/admin/listado-barcos-disponibles';
import { AddBarco } from '../../modelos/admin/add-barco';
import { EditarBarco } from '../../modelos/admin/editar-barco';
import { RegistroAdminResponse } from '../../modelos/admin/registro-admin';
import { LogoutAdmin } from '../../modelos/admin/logout-admin';
import { MotorBusqueda } from '../../modelos/admin/motor-busqueda';
import { ListadoSocios } from '../../modelos/admin/list-socios';
import { EditarPerfilAdmin } from '../../modelos/admin/editar-perfil-admin';

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {

  constructor(private http:HttpClient) { }

  getAdminLogued(): Observable<AdminLoguedResponse>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<AdminLoguedResponse>(`${environment.HeadUrl}/administrador/logueado`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }

    })
  }

  listarBarcos(): Observable<ListadoBarcosAdmin[]>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<ListadoBarcosAdmin[]>(`${environment.HeadUrl}/administrador/listar/barcos`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  listarBracosDisponibles(): Observable<ListadoBarcosDisponibles[]>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<ListadoBarcosDisponibles[]>(`${environment.HeadUrl}/administrador/listar/barcos/no/ocupado`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  listadoBarcosOcupados(): Observable<ListadoBarcosDisponibles[]>{
    let token = localStorage.getItem('TOKEN');
    return this.http.get<ListadoBarcosDisponibles[]>(`${environment.HeadUrl}/administrador/listar/barcos/ocupado`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }
  addBarco(matricula:string,nombre:string,numeroAmarre:number,cuota:number): Observable<AddBarco>{
    let token = localStorage.getItem('TOKEN');
    return this.http.post<AddBarco>(`${environment.HeadUrl}/administrador/crear/barco`,{
      "matricula":`${matricula}`,
      "nombre":`${nombre}`,
      "numeroAmarre":`${numeroAmarre}`,
      "cuota":`${cuota}`
    },{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })

  }

  editarBarco(id:string,matricula:string,nombre:string,numeroAmarre:number,cuota:number): Observable<EditarBarco>{
    let token = localStorage.getItem('TOKEN');
    return this.http.put<EditarBarco>(`${environment.HeadUrl}/administrador/editar/barco/${id}`,{
      "matricula":`${matricula}`,
      "nombre":`${nombre}`,
      "numeroAmarre":`${numeroAmarre}`,
      "cuota":`${cuota}`
    },{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })

  }

  deleteBarco(id:string){
    let token = localStorage.getItem('TOKEN');
    return this.http.delete(`${environment.HeadUrl}/administrador/eliminar/barco/${id}`,{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
  }

  registroAdmin(email:string,fotoUrl:string,name:string,lastName:string,phoneNumber:string): Observable<RegistroAdminResponse>{
    return this.http.post<RegistroAdminResponse>(`${environment.HeadUrl}/auth/register/admin`,{
      "email":`${email}`,
      "fotoUrl":`${fotoUrl}`,
      "name":`${name}`,
      "lastName":`${lastName}`,
      "phoneNumber":`${phoneNumber}`,
    })
  }
  
  logOutAdmin(): Observable<LogoutAdmin>{
    let token = localStorage.getItem('TOKEN');
    localStorage.removeItem('TOKEN');
    return this.http.post<LogoutAdmin>(`${environment.HeadUrl}/administrador/logout`,{},{
      headers: {
        accept: 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })
}
motorBusqueda(busqueda:string): Observable<MotorBusqueda[]>{
  let token = localStorage.getItem('TOKEN');
  return this.http.get<MotorBusqueda[]>(`${environment.HeadUrl}/administrador/buscar/${busqueda}`,{
    headers: {
      accept: 'application/json',
      'Authorization': `Bearer ${token}`
    }
  })
}
listadoSocios(): Observable<ListadoSocios[]>{
  let token = localStorage.getItem('TOKEN');
  return this.http.get<ListadoSocios[]>(`${environment.HeadUrl}/administrador/ver/socios`,{
    headers: {
      accept: 'application/json',
      'Authorization': `Bearer ${token}`
    }
  })
}

bannearSocio(id:string){
  let token = localStorage.getItem('TOKEN');
  return this.http.delete(`${environment.HeadUrl}/administrador/eliminar/socio/${id}`,{
    headers: {
      accept: 'application/json',
      'Authorization': `Bearer ${token}`
    }
  })
}

editarPerfil(name:string,lastName:string,fotoUrl:string): Observable<EditarPerfilAdmin>{
  let token = localStorage.getItem('TOKEN');
  return this.http.put<EditarPerfilAdmin>(`${environment.HeadUrl}/administrador/editar`,{
    
    "fotoUrl":`${fotoUrl}`,
    "name":`${name}`,
    "lastName":`${lastName}`,
  },{
    headers: {
      accept: 'application/json',
      'Authorization': `Bearer ${token}`
    }
  })
}

  
}
