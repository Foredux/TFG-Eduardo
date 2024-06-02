package com.example.ejercicioApartado2punto7.Controller;

import com.example.ejercicioApartado2punto7.Dto.GetListBarcosNoOcupados;
import com.example.ejercicioApartado2punto7.Dto.GetListaBarcosDto;
import com.example.ejercicioApartado2punto7.Dto.PostEditarBarcoDto;
import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Service.BarcoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BarcoController {
    private final BarcoService barcoService;
    @PostMapping("/administrador/crear/barco")
    public ResponseEntity<?> crearBarco( @RequestBody Barco barco){
        try{
            Barco barco1 = barcoService.crearBarco(barco);
            return ResponseEntity.status(201).body(barco1);
        }catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya hay un barco con esa matricula");
        }

    }

    @PostMapping("/socio/asignar/barco/{idBarco}")
    public ResponseEntity<?> asignarBarco(@PathVariable UUID idBarco){
        try{
            Barco barco = barcoService.asignarBarco(idBarco);
            return ResponseEntity.status(201).body(barco);
        }catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No tienes suficiente saldo");
        }


    }


    @GetMapping("/socio/listar/barcos")
    public ResponseEntity<List<GetListBarcosNoOcupados>> listarBarcos(){
        List<GetListBarcosNoOcupados> barcos = barcoService.listarBarcosNoOcupadosSocio();
        return ResponseEntity.ok(barcos);
    }

    @GetMapping("/administrador/listar/barcos")
    public ResponseEntity<List<GetListaBarcosDto>> listarBarcosAdmin(){
        List<GetListaBarcosDto> barcos = barcoService.listadoDeBarcos();
        return ResponseEntity.ok(barcos);
    }

    @GetMapping("administrador/listar/barcos/no/ocupado")
    public ResponseEntity<List<GetListBarcosNoOcupados>> listadoBarcosNoOcupadosAdmin(){
        List<GetListBarcosNoOcupados> getListBarcosNoOcupados = barcoService.listarBarcosNoOcupadosAdmin();
        return ResponseEntity.ok(getListBarcosNoOcupados);
    }
    @GetMapping("administrador/listar/barcos/ocupado")
    public ResponseEntity<List<GetListBarcosNoOcupados>> listadoBarcosOcupadosAdmin(){
        List<GetListBarcosNoOcupados> getListBarcosNoOcupados = barcoService.listarBarcosOcupadosAdmin();
        return ResponseEntity.ok(getListBarcosNoOcupados);
    }

    @GetMapping("/socio/buscar/barco/{matricula}")
    public ResponseEntity<Barco> buscarBarcoPorMatricula(@PathVariable String matricula){
        Barco barco = barcoService.findByMatricula(matricula);
        return ResponseEntity.ok(barco);
    }
    @GetMapping("/administrador/buscar/barco/{matricula}")
    public ResponseEntity<Barco> buscarBarcoPorMatriculaAdmin(@PathVariable String matricula){
        Barco barco = barcoService.findByMatricula(matricula);
        return ResponseEntity.ok(barco);
    }
    @PutMapping("/administrador/editar/barco/{id}")
    public ResponseEntity<?> editarBarco(@PathVariable UUID id,@RequestBody PostEditarBarcoDto barco){
        try {
            Barco barco1 = barcoService.editarBarco(id,barco);
            return ResponseEntity.status(201).body(barco1);
        }catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya hay un barco con esa matricula");
        }

    }
    @GetMapping("/administrador/buscar/{busqueda}")
    public ResponseEntity<List<GetListBarcosNoOcupados>> busquedaPorNombreoMatricula(@PathVariable String busqueda){
        List<GetListBarcosNoOcupados> getListBarcosNoOcupados = barcoService.BuscarPorNombreOPorMatricula(busqueda);
        return ResponseEntity.ok(getListBarcosNoOcupados);
    }
    @DeleteMapping("administrador/eliminar/barco/{id}")
    public ResponseEntity<?> eliminarBarco(@PathVariable UUID id){
        barcoService.eliminarBarco(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("socio/desasignar/barco/{idBarco}")
    public ResponseEntity<Barco> desasiganrBarco(@PathVariable UUID idBarco){
        Barco barco = barcoService.desasignarBarco(idBarco);
        return ResponseEntity.status(201).body(barco);
    }


}
