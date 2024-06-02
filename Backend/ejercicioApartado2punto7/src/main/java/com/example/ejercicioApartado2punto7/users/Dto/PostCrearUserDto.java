package com.example.ejercicioApartado2punto7.users.Dto;

import java.time.LocalDate;
import java.util.EnumSet;

public record PostCrearUserDto(String email,
                               String name,
                               String lastName,
                               String password,
                               String username,
                               String fotoUrl,
                               String phoneNumber,
                               double saldo,
                               LocalDate nacimiento) {
}
