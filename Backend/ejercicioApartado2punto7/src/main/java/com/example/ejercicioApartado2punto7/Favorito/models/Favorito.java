package com.example.ejercicioApartado2punto7.Favorito.models;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorito {
    @EmbeddedId
    private FavoritoId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JsonBackReference(value="socio-favorito")
    private Socio usuario;

    @ManyToOne
    @MapsId("comercioId")
    @JsonBackReference(value="barco-favorito")
    private Barco comercio;
}
