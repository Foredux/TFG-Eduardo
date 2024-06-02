package com.example.ejercicioApartado2punto7.Dto;

import java.time.LocalDateTime;

public record GetSalidas(String destino,
                         String nombrePatron,
                         LocalDateTime fechaHoraSalida,
                         LocalDateTime fechaHoraLlegada) {
}
