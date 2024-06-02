package com.example.ejercicioApartado2punto7.Model;

import com.example.ejercicioApartado2punto7.Favorito.models.Favorito;
import com.example.ejercicioApartado2punto7.users.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Socio extends User {



    private double saldo;


    @OneToMany(mappedBy = "socio")
    @JsonManagedReference(value="socio-barco")
    private List<Barco> barcos;

    @ManyToMany
    @JoinTable(name = "tbl_usuario_usuarios",
            joinColumns = @JoinColumn(name = "responsable_id"),
            inverseJoinColumns = @JoinColumn(name = "socios_id"))
    private List<Socio> usuarios = new ArrayList<>();
    @ManyToMany(mappedBy = "usuarios")
    private List<Socio> inChargeof;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference(value="socio-favorito")
    private List<Favorito> favoritos;
}
