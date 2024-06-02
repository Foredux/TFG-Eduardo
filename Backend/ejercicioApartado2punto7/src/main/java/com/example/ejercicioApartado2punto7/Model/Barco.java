package com.example.ejercicioApartado2punto7.Model;

import com.example.ejercicioApartado2punto7.Favorito.models.Favorito;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Barco {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;
    @Column(unique = true)
    private String matricula;
    private String nombre;
    private boolean ocupado;
    @Column(name = "numero_amarre")
    private int numeroAmarre;
    private double cuota;

    private boolean likes;

    @OneToMany(mappedBy = "comercio", cascade = CascadeType.ALL)
    @JsonManagedReference(value="barco-favorito")
    protected List<Favorito> favoritoList;

    @ManyToOne
    @JsonBackReference(value="socio-barco")
    private Socio socio;

    @Column(name = "salida")
    @OneToMany(mappedBy = "barco", cascade = CascadeType.ALL)
    @JsonManagedReference(value="barco-salida")
    private List<Salida> salidas;





}
