import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListadoBarcoDisponible } from '../../modelos/socios/listar-barcos-disponibles';
import { environment } from '../../environments/environment.developments';
import { RegistroResponseSocio } from '../../modelos/socios/registro-response';
import { LoginResponseSocio } from '../../modelos/socios/login-socio';
import { SocioLogueadoResponse } from '../../modelos/socios/socio-logueado';
import { LogoutSocioResponse } from '../../modelos/socios/logout-socio';
import { AsignarBarcosResponse } from '../../modelos/socios/asignar-barco-response';
import { CrearSalidaResponse } from '../../modelos/socios/crear-salida';
import { BarcosAsociadosResponse } from '../../modelos/socios/ver-barcos-asociados';
import { DarLikeResponse } from '../../modelos/socios/dar-like-response';
import { VerLikesResponse } from '../../modelos/socios/ver-like-response';
import { EditarSalida } from '../../modelos/socios/editar-salida';
import { DesasignarBarco } from '../../modelos/socios/des-asignar-barco';
import { EditarSocio } from '../../modelos/socios/editar-socio';

@Injectable({
  providedIn: 'root',
})
export class SocioService {
  constructor(private http: HttpClient) {}

  registro(
    email: string,
    name: string,
    username: string,
    lastName: string,
    password: string,
    fotoUrl: string,
    phoneNumber: string
  ): Observable<RegistroResponseSocio> {
    return this.http.post<RegistroResponseSocio>(
      `${environment.HeadUrl}/auth/register/user`,
      {
        email: `${email}`,
        fotoUrl: `${fotoUrl}`,
        name: `${name}`,
        password: `${password}`,
        lastName: `${lastName}`,
        username: `${username}`,
        phoneNumber: `${phoneNumber}`,
      }
    );
  }
  loginSocio(email: string, password: string): Observable<LoginResponseSocio> {
    return this.http.post<LoginResponseSocio>(
      `${environment.HeadUrl}/auth/login/user`,
      {
        email: `${email}`,
        password: `${password}`,
      }
    );
  }

  listarBarcosDisponibles(): Observable<ListadoBarcoDisponible[]> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.get<ListadoBarcoDisponible[]>(
      `${environment.HeadUrl}/socio/listar/barcos`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  socioLogueado(): Observable<SocioLogueadoResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.get<SocioLogueadoResponse>(
      `${environment.HeadUrl}/socio/logueado`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  socioLogout(): Observable<LogoutSocioResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    localStorage.removeItem('TOKEN_SOCIO');
    return this.http.post<LogoutSocioResponse>(
      `${environment.HeadUrl}/socio/logout`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  asignarBarco(id: string): Observable<AsignarBarcosResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.post<AsignarBarcosResponse>(
      `${environment.HeadUrl}/socio/asignar/barco/${id}`,
      {
        id: `${id}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  crearSalida(
    id: string,
    destino: string,
    nombrePatron: string
  ): Observable<CrearSalidaResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.post<CrearSalidaResponse>(
      `${environment.HeadUrl}/socio/crear/salida/${id}`,
      {
        id: `${id}`,
        destino: `${destino}`,
        nombrePatron: `${nombrePatron}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  

  verBarcosAsociados(): Observable<BarcosAsociadosResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.get<BarcosAsociadosResponse>(
      `${environment.HeadUrl}/socio/ver/barcos/asiganados`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  darLike(id: string): Observable<DarLikeResponse> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.post<DarLikeResponse>(
      `${environment.HeadUrl}/socio/dar/like/${id}`,
      {
        id: `${id}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }
  verLikes(): Observable<VerLikesResponse[]> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.get<VerLikesResponse[]>(
      `${environment.HeadUrl}/socio/ver/likes`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }
  quitarLike(id: string) {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.delete(`${environment.HeadUrl}/socio/quitar/like/${id}`, {
      headers: {
        accept: 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
  }

  eliminarSalida(id: string){
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.delete(
      `${environment.HeadUrl}/eliminar/salida/${id}`,
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  finalizarSalida(id: string): Observable<EditarSalida> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.put<EditarSalida>(
      `${environment.HeadUrl}/socio/editar/salida/${id}`,
      {
        id: `${id}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }
  desasignarBarco(id: string): Observable<DesasignarBarco> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.post<DesasignarBarco>(
      `${environment.HeadUrl}/socio/desasignar/barco/${id}`,
      {
        id: `${id}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }

  socioEditar(
    name: string,
    lastName: string,
    saldo: number,
    fotoUrl: string,
    phoneNumber: string,
    username: string
  ): Observable<EditarSocio> {
    let token = localStorage.getItem('TOKEN_SOCIO');
    return this.http.put<EditarSocio>(
      `${environment.HeadUrl}/editar/socio`,
      {
        name: `${name}`,
        lastName: `${lastName}`,
        saldo: `${saldo}`,
        fotoUrl: `${fotoUrl}`,
        phoneNumber: `${phoneNumber}`,
        username: `${username}`,
      },
      {
        headers: {
          accept: 'application/json',
          Authorization: `Bearer ${token}`,
        },
      }
    );
  }
}
