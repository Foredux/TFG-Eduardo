import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginAdminResponse } from '../../modelos/admin/login-admin-response';
import { environment } from '../../environments/environment.developments';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http:HttpClient) { }

  loginAdmin(email:string,password:string): Observable<LoginAdminResponse>{
    return this.http.post<LoginAdminResponse>(`${environment.HeadUrl}/auth/login/admin`,
    {
      "email":`${email}`,
      "password":`${password}`
    });
  }
}
