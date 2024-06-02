package com.example.ejercicioApartado2punto7.Favorito.repository;

import com.example.ejercicioApartado2punto7.Favorito.models.Favorito;
import com.example.ejercicioApartado2punto7.Favorito.models.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepo extends JpaRepository<Favorito, FavoritoId> {
}
