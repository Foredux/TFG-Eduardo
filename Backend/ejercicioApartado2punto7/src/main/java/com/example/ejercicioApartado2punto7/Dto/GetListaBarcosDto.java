package com.example.ejercicioApartado2punto7.Dto;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Socio;

import java.util.UUID;

public record GetListaBarcosDto(UUID uuid,
        String matricula,
                                String nombre,
                                double cuota,
                                int numeroAmarre,

                                String nombreSocio,
                                boolean ocupado,
                                int salidas


                                ) {

    public static GetListaBarcosDto of(Barco b){
        return new GetListaBarcosDto(
                b.getId(),
                b.getMatricula(),
                b.getNombre(),
                b.getCuota(),
                b.getNumeroAmarre(),
                b.getSocio() == null ? "No hay socios": b.getSocio().getName(),
                b.isOcupado(),
                b.getSalidas().size()
        );
    }
}
