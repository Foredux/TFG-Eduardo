export interface EditarBarco {
    id:           string;
    matricula:    string;
    nombre:       string;
    ocupado:      boolean;
    numeroAmarre: number;
    cuota:        number;
    likes:        boolean;
    favoritoList: any[];
    salidas:      any[];
}
