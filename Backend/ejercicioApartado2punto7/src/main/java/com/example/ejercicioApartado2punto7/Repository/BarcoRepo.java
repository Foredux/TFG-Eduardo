package com.example.ejercicioApartado2punto7.Repository;

import com.example.ejercicioApartado2punto7.Dto.GetListaBarcosDto;
import com.example.ejercicioApartado2punto7.Model.Barco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BarcoRepo extends JpaRepository<Barco, UUID> {

    @Query("""
            select b 
            from Barco b
            where b.matricula = ?1
            """)
    Optional<Barco> findByMatricula(String matricula);

    List<Barco> findAllByNombreIgnoreCase(String nombre);
    Optional<Barco> findByMatriculaIgnoreCase(String matricula);
    @Query("""
            select b
            from Barco b 
            where b.ocupado = false
            """)
    List<Barco> listarBarcosNoOcupados();


    @Query("""
            select new com.example.ejercicioApartado2punto7.Dto.GetListaBarcosDto(
            b.matricula,
              b.nombre,
              b.cuota,
              b.numeroAmarre,
              b.socio,
              b.salidas
              
              
              )
              from Barco b
            """)
    List<GetListaBarcosDto> ListadoBarcos();
}
