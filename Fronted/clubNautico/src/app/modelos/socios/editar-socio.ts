export interface EditarSocio {
    id:                    string;
    email:                 string;
    username:              string;
    password:              string;
    name:                  string;
    lastName:              null;
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
    barcos:                any[];
    usuarios:              any[];
    inChargeof:            any[];
    favoritos:             Favorito[];
    authorities:           Authority[];
}

export interface Authority {
    authority: string;
}

export interface Favorito {
    id: ID;
}

export interface ID {
    usuarioId:  string;
    comercioId: string;
}
