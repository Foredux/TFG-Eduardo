package com.example.ejercicioApartado2punto7.Favorito.controller;

import com.example.ejercicioApartado2punto7.Dto.GetListBarcosNoOcupados;
import com.example.ejercicioApartado2punto7.Favorito.service.FavoritoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;

    @PostMapping("socio/dar/like/{barcoId}")
    public ResponseEntity<GetListBarcosNoOcupados> darLike(@PathVariable UUID barcoId){
        GetListBarcosNoOcupados getListBarcosNoOcupados = favoritoService.darLike(barcoId);
        return ResponseEntity.status(201).body(getListBarcosNoOcupados);
    }

    @GetMapping("socio/ver/likes")
    public ResponseEntity<List<GetListBarcosNoOcupados>> verLikes(){
        List<GetListBarcosNoOcupados> getListBarcosNoOcupados = favoritoService.listarLikes();
        return ResponseEntity.ok(getListBarcosNoOcupados);
    }

    @DeleteMapping("socio/quitar/like/{barcoId}")
    public ResponseEntity<?> quitarLike(@PathVariable UUID barcoId){
        favoritoService.quitarLike(barcoId);
        return ResponseEntity.status(204).build();
    }
}
