export interface EditarPerfilAdmin {
    id:                    string;
    email:                 string;
    username:              string;
    password:              string;
    name:                  string;
    lastName:              string;
    phoneNumber:           string;
    birthDate:             Date;
    confirmationToken:     string;
    fotoUrl:               null;
    accountNonExpired:     boolean;
    accountNonLocked:      boolean;
    credentialsNonExpired: boolean;
    enabled:               boolean;
    roles:                 string[];
    createdAt:             Date;
    lastPasswordChangeAt:  Date;
    puesto:                string;
    authorities:           Authority[];
}

export interface Authority {
    authority: string;
}
