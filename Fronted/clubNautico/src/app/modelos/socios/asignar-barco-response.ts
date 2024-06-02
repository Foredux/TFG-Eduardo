export interface AsignarBarcosResponse {
    id:           string;
    matricula:    string;
    nombre:       string;
    ocupado:      boolean;
    numeroAmarre: number;
    cuota:        number;
    likes:        boolean;
    salidas:      any[];
}
