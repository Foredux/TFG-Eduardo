import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from './pages/login/page-login/page-login.component';
import { PageHomePageComponent } from './pages/administrador/homepage/page-home-page/page-home-page.component';
import { PageListadoBarcosComponent } from './pages/administrador/barcos/page-listado-barcos/page-listado-barcos.component';
import { PageAddBarcoComponent } from './pages/administrador/barcos/page-add-barco/page-add-barco.component';
import { PageEditarBarcoComponent } from './pages/administrador/barcos/page-editar-barco/page-editar-barco.component';
import { PageRegistroAdminComponent } from './pages/login/page-registro-admin/page-registro-admin.component';
import { AuthGuardComponent } from './componentes/administrador/auth-guard/auth-guard.component';
import { AuthGuardService } from './servicios/authguard/auth-guard.service';
import { PageListSociosComponent } from './pages/administrador/barcos/page-list-socios/page-list-socios.component';
import { PageEditarPerfilComponent } from './pages/administrador/perfil/page-editar-perfil/page-editar-perfil.component';
import { PagePerfilAdminComponent } from './pages/administrador/perfil/page-perfil-admin/page-perfil-admin.component';
import { PageRegistroSocioComponent } from './pages/login/page-regsitro-socio/page-registro-socio.component';
import { PageLoginSocioComponent } from './pages/login/page-login-socio/page-login-socio.component';
import { RevisarEmailComponent } from './componentes/revisar-email/revisar-email.component';
import { HomePageSociosComponent } from './pages/socios/homepage/home-page-socios/home-page-socios.component';
import { PageVerBarcosComponent } from './pages/socios/barcos/page-ver-barcos/page-ver-barcos.component';
import { PageSalidaComponent } from './pages/socios/salida/page-salida/page-salida.component';
import { PageCrearSalidaComponent } from './pages/socios/salida/page-crear-salida/page-crear-salida.component';
import { PageListarLikesComponent } from './pages/socios/barcos/page-listar-likes/page-listar-likes.component';
import { PageVerPerfilComponent } from './pages/socios/perfil/page-ver-perfil/page-ver-perfil.component';

const routes: Routes = [
  { path: 'home-page-admin', component: PageHomePageComponent, canActivate: [AuthGuardService] },
  { path: 'listado-barcos', component: PageListadoBarcosComponent, canActivate: [AuthGuardService] },
  { path: 'registro-admin', component: PageRegistroAdminComponent, canActivate: [AuthGuardService] },
  { path: 'editar-barco/:id', component: PageEditarBarcoComponent, canActivate: [AuthGuardService] },
  { path: 'add-barco', component: PageAddBarcoComponent, canActivate: [AuthGuardService] },
  { path: 'listado-socios', component: PageListSociosComponent, canActivate: [AuthGuardService] },
  { path: 'perfil-admin', component: PagePerfilAdminComponent, canActivate: [AuthGuardService] },
  { path: 'editar-perfil-admin', component: PageEditarPerfilComponent, canActivate: [AuthGuardService] },
  { path: 'home-page-socio', component: HomePageSociosComponent, canActivate: [AuthGuardService] },
  { path: 'barcos-disponibles', component: PageVerBarcosComponent, canActivate: [AuthGuardService] },
  { path: 'crear-salida', component: PageSalidaComponent, canActivate: [AuthGuardService] },
  { path: 'likes', component: PageListarLikesComponent, canActivate: [AuthGuardService] },
  { path: 'crear-salida/:idBarco', component: PageCrearSalidaComponent, canActivate: [AuthGuardService] },
  { path: 'ver-perfil', component: PageVerPerfilComponent, canActivate: [AuthGuardService] },

  { path: 'login', component: PageLoginComponent },
  { path: 'confirmar-email', component: RevisarEmailComponent },
  { path: 'registro-socio', component: PageRegistroSocioComponent },
  { path: 'login-socio', component: PageLoginSocioComponent },
  { path: '**', redirectTo: '/registro-socio', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
