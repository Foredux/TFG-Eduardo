export interface ListadoSocios {
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
    barcos:                any[];
    usuarios:              any[];
    inChargeof:            any[];
    authorities:           Authority[];
}

export interface Authority {
    authority: string;
}
