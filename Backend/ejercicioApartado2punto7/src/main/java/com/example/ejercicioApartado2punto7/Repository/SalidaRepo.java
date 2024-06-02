package com.example.ejercicioApartado2punto7.Repository;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Salida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SalidaRepo extends JpaRepository<Salida, UUID> {

    List<Salida> findByBarco(Barco barco);
}
