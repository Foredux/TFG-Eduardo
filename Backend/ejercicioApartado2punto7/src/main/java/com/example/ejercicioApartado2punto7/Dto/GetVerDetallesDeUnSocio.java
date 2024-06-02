package com.example.ejercicioApartado2punto7.Dto;

import com.example.ejercicioApartado2punto7.Model.Barco;

import java.util.List;

public record GetVerDetallesDeUnSocio(String nombre,
                                      String apellidos,
                                      List<Barco> barcos) {
}
