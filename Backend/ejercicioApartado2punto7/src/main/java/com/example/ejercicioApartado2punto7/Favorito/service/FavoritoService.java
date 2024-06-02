package com.example.ejercicioApartado2punto7.Favorito.service;

import com.example.ejercicioApartado2punto7.Dto.GetListBarcosNoOcupados;
import com.example.ejercicioApartado2punto7.Favorito.models.Favorito;
import com.example.ejercicioApartado2punto7.Favorito.models.FavoritoId;
import com.example.ejercicioApartado2punto7.Favorito.repository.FavoritoRepo;
import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Repository.BarcoRepo;
import com.example.ejercicioApartado2punto7.Repository.SocioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepo favoritoRepo;
    private final SocioRepo socioRepo;
    private final BarcoRepo barcoRepo;

    public GetListBarcosNoOcupados darLike(UUID barcoId){
        Optional<Barco> barco = barcoRepo.findById(barcoId);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> administrador = socioRepo.findByEmailIgnoreCase(nombre);
            if (administrador.isPresent()){

                Favorito favorito = Favorito.builder()
                        .id(new FavoritoId(administrador.get().getId(),barcoId))
                        .comercio(barco.get())
                        .usuario(administrador.get())

                        .build();
                barco.get().setLikes(true);
                favoritoRepo.save(favorito);

                return barco.map(GetListBarcosNoOcupados::of).get();
            }


        }

        return null;
    }

    public List<GetListBarcosNoOcupados> listarLikes(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> administrador = socioRepo.findByEmailIgnoreCase(nombre);
            List<Barco> barcos = administrador.get().getFavoritos().stream().map(Favorito::getComercio).collect(Collectors.toList());
          List<GetListBarcosNoOcupados> getListBarcosNoOcupados =  barcos.stream().map(GetListBarcosNoOcupados::of).toList();
            return getListBarcosNoOcupados;

        }

        return null;
    }

    public void quitarLike(UUID barcoId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Barco> barco = barcoRepo.findById(barcoId);
        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> administrador = socioRepo.findByEmailIgnoreCase(nombre);
            Optional<Favorito> favorito = favoritoRepo.findById(new FavoritoId(administrador.get().getId(),barcoId));
            if (favorito.isPresent()){
                barco.get().setLikes(false);
                favoritoRepo.delete(favorito.get());
            }


        }

        ;
    }


}

