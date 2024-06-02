export interface BarcosAsociadosResponse {
    id:                    string;
    email:                 string;
    username:              string;
    password:              string;
    name:                  string;
    lastName:              string;
    phoneNumber:           string;
    birthDate:             Date;
    confirmationToken:     string;
    fotoUrl:               string;
    accountNonExpired:     boolean;
    accountNonLocked:      boolean;
    credentialsNonExpired: boolean;
    enabled:               boolean;
    roles:                 string[];
    createdAt:             Date;
    lastPasswordChangeAt:  Date;
    saldo:                 number;
    barcos:                Barco[];
    usuarios:              any[];
    inChargeof:            any[];
    favoritos:             any[];
    authorities:           Authority[];
}

export interface Authority {
    authority: string;
}

export interface Barco {
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
    fechaHoraLlegada: Date;
    destino:          string;
    nombrePatron:     string;
}
