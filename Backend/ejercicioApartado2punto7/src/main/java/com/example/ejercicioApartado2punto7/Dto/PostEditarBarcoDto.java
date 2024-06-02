package com.example.ejercicioApartado2punto7.Dto;

import com.example.ejercicioApartado2punto7.Model.Socio;

public record PostEditarBarcoDto(String matricula,
                                 String nombre,
                                 double cuota,
                                 int numeroAmarre
                                 ) {
}
