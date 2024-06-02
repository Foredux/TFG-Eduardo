export interface DesasignarBarco {
    id:           string;
    matricula:    string;
    nombre:       string;
    ocupado:      boolean;
    numeroAmarre: number;
    cuota:        number;
    likes:        boolean;
    favoritoList: any[];
    salidas:      Salida[];
}

export interface Salida {
    id:               string;
    fechaHoraSalida:  Date;
    fechaHoraLlegada: null;
    destino:          string;
    nombrePatron:     string;
}
