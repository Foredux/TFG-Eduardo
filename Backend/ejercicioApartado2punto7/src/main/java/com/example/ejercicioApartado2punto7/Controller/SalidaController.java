package com.example.ejercicioApartado2punto7.Controller;

import com.example.ejercicioApartado2punto7.Model.Salida;
import com.example.ejercicioApartado2punto7.Service.SalidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SalidaController {
    private final SalidaService salidaService;

    @PostMapping("/socio/crear/salida/{idBarco}")
    public ResponseEntity<Salida> crearSalida(@RequestBody Salida salida,@PathVariable UUID idBarco){
        Salida salida1 = salidaService.crearSalida(salida,idBarco);
        return ResponseEntity.status(201).body(salida1);
    }
    @GetMapping("/listar/salidas")
    public ResponseEntity<List<Salida>> listarSalidas(){
        List<Salida> salidas = salidaService.listarSalida();
        return ResponseEntity.ok(salidas);
    }

    @GetMapping("/buscar/salida/{id}")
    public ResponseEntity<Salida> buscarSaldiaById(@PathVariable UUID id){
        Salida salida = salidaService.findById(id);
        return ResponseEntity.ok(salida);
    }
    @PutMapping("socio/editar/salida/{id}")
    public ResponseEntity<Salida> editarSalida(@PathVariable UUID id,@RequestBody Salida salida1){
        Salida salida = salidaService.editarSalida(id,salida1);
        return ResponseEntity.status(201).body(salida);
    }
    @DeleteMapping("/eliminar/salida/{id}")
    public ResponseEntity<?> eliminarSalida(@PathVariable UUID id){
        salidaService.eliminarSalida(id);
     return    ResponseEntity.status(204).build();
    }
}
