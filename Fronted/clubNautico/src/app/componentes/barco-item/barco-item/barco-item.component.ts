import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ListadoBarcosAdmin } from '../../../modelos/admin/listado-barcos-admin';
import { AdministradorService } from '../../../servicios/administrador/administrador.service';

@Component({
  selector: 'app-barco-item',
  templateUrl: './barco-item.component.html',
  styleUrl: './barco-item.component.css'
})
export class BarcoItemComponent {

  @Input() barco!:ListadoBarcosAdmin;
  @Output() comercioClick = new EventEmitter<String>();

  constructor (private adminService: AdministradorService){}

  

}
