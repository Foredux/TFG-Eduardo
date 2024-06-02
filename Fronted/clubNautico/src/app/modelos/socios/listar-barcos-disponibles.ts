export interface ListadoBarcoDisponible {
    uuid:         string;
    nombre:       string;
    matricula:    string;
    cuota:        number;
    numeroAmarre: number;
    nombreSocio:  string;
    ocupado:      boolean;
    salidas:      number;
    likes:        boolean;
}
