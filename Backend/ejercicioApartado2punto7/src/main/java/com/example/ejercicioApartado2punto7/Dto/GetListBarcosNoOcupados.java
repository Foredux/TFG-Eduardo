package com.example.ejercicioApartado2punto7.Dto;

import com.example.ejercicioApartado2punto7.Model.Barco;

import java.util.UUID;

public record GetListBarcosNoOcupados(UUID uuid,
                                      String nombre,
                                      String matricula,
                                      double cuota,
                                      int numeroAmarre,
                                      String nombreSocio,
                                      boolean ocupado,
                                      int salidas,
                                      boolean likes) {
    public static GetListBarcosNoOcupados of(Barco b){
        return new GetListBarcosNoOcupados(
                b.getId(),
                b.getNombre(),
                b.getMatricula(),
                b.getCuota(),
                b.getNumeroAmarre(),
                b.getSocio() == null ? "No hay socios": b.getSocio().getName(),
                b.isOcupado(),
                b.getSalidas().size(),
                b.isLikes()
        );
    }
}
