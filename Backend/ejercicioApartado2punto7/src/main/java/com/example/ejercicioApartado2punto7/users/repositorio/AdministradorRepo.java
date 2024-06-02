package com.example.ejercicioApartado2punto7.users.repositorio;

import com.example.ejercicioApartado2punto7.users.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdministradorRepo extends JpaRepository<Administrador, UUID> {
    Optional<Administrador> findFirstByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);

    Optional<Administrador> findByEmailIgnoreCase(String nombre);



}
