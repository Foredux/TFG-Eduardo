package com.example.ejercicioApartado2punto7.Repository;

import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.users.Dto.GetUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SocioRepo extends JpaRepository<Socio,UUID> {
    Optional<Socio> findByNameIgnoreCase(String nombre);
    Optional<Socio> findFirstByEmail(String email);
    Socio findByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<Socio> findByEnabledFalse();
    Optional<Socio> findByEmailIgnoreCase(String nombre);
    List<Socio> findByEnabledTrue();
    Socio findByConfirmationToken(String confirmationToken);
    @Query("""
            select new com.example.ejercicioApartado2punto7.users.Dto.GetUsuario(
            u.id,
            u.username,
              u.name,
              u.lastName,
              u.phoneNumber,
              u.fotoUrl
            
            )
            from Socio u
            where u.id = ?1
            """)
    GetUsuario getUsuario(UUID uuid);

    @Query("""
            select new com.example.ejercicioApartado2punto7.Dto.GetVerDetallesDeUnSocio(
            s.name,
            s.lastName,
            s.barcos
            )
            from Socio s
            """)
    Socio detallesDeUnSocio();

}
