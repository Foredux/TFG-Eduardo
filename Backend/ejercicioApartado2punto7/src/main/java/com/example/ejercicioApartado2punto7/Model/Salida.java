package com.example.ejercicioApartado2punto7.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Salida {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private String destino;
    private String nombrePatron;

    @ManyToOne
    @JsonBackReference(value="barco-salida")
    private Barco barco;
}
