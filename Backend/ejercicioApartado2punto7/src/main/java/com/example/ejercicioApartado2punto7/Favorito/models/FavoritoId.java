package com.example.ejercicioApartado2punto7.Favorito.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoritoId implements Serializable {

    private UUID usuarioId;
    private UUID comercioId;
}