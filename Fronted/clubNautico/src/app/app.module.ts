import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { PageLoginComponent } from './pages/login/page-login/page-login.component';
import { HeaderComponent } from './componentes/header/header/header.component';
import { PageHomePageComponent } from './pages/administrador/homepage/page-home-page/page-home-page.component';
import { PageListadoBarcosComponent } from './pages/administrador/barcos/page-listado-barcos/page-listado-barcos.component';
import { BarcoItemComponent } from './componentes/barco-item/barco-item/barco-item.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatRadioModule } from '@angular/material/radio';
import { FormsModule } from '@angular/forms';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { CrearBarcoComponent } from './componentes/crear-barco/crear-barco/crear-barco.component';
import { PageAddBarcoComponent } from './pages/administrador/barcos/page-add-barco/page-add-barco.component';
import { PageEditarBarcoComponent } from './pages/administrador/barcos/page-editar-barco/page-editar-barco.component';
import { PageRegistroAdminComponent } from './pages/login/page-registro-admin/page-registro-admin.component';
import { AuthGuardComponent } from './componentes/administrador/auth-guard/auth-guard.component';
import { PageListSociosComponent } from './pages/administrador/barcos/page-list-socios/page-list-socios.component';
import { PageEditarPerfilComponent } from './pages/administrador/perfil/page-editar-perfil/page-editar-perfil.component';
import { PagePerfilAdminComponent } from './pages/administrador/perfil/page-perfil-admin/page-perfil-admin.component';
import { PageRegistroSocioComponent } from './pages/login/page-regsitro-socio/page-registro-socio.component';
import { PageLoginSocioComponent } from './pages/login/page-login-socio/page-login-socio.component';
import { RevisarEmailComponent } from './componentes/revisar-email/revisar-email.component';
import { HomePageSociosComponent } from './pages/socios/homepage/home-page-socios/home-page-socios.component';
import { HeaderSocioComponent } from './componentes/header/header-socio/header-socio/header-socio.component';
import { PageVerBarcosComponent } from './pages/socios/barcos/page-ver-barcos/page-ver-barcos.component';
import { PageSalidaComponent } from './pages/socios/salida/page-salida/page-salida.component';
import { PageCrearSalidaComponent } from './pages/socios/salida/page-crear-salida/page-crear-salida.component';
import { PageListarLikesComponent } from './pages/socios/barcos/page-listar-likes/page-listar-likes.component';
import { PageVerPerfilComponent } from './pages/socios/perfil/page-ver-perfil/page-ver-perfil.component';
import { PageEditarPerfilSocioComponent } from './pages/socios/perfil/page-editar-perfil/page-editar-perfil.component';

@NgModule({
  declarations: [
    AppComponent,
    PageLoginComponent,
    HeaderComponent,
    PageHomePageComponent,
    PageListadoBarcosComponent,
    BarcoItemComponent,
    CrearBarcoComponent,
    PageAddBarcoComponent,
    PageEditarBarcoComponent,
    PageRegistroAdminComponent,
    AuthGuardComponent,
    PageListSociosComponent,
    PageEditarPerfilComponent,
    PagePerfilAdminComponent,
    PageRegistroSocioComponent,
    PageLoginSocioComponent,
    RevisarEmailComponent,
    HomePageSociosComponent,
    HeaderSocioComponent,
    PageVerBarcosComponent,
    PageSalidaComponent,
    PageCrearSalidaComponent,
    PageListarLikesComponent,
    PageVerPerfilComponent
  ],
  imports: [
    BrowserModule,
    
    MatSlideToggleModule,
    ReactiveFormsModule,
    FormsModule,
    MatRadioModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
