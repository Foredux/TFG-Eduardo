package com.example.ejercicioApartado2punto7.Service;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Salida;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Repository.BarcoRepo;
import com.example.ejercicioApartado2punto7.Repository.SalidaRepo;
import com.example.ejercicioApartado2punto7.Repository.SocioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalidaService {
    private final SalidaRepo salidaRepo;
    private final BarcoRepo barcoRepo;
    private final SocioRepo socioRepo;

    public Salida crearSalida(Salida salida, UUID idBarco) {
        Optional<Barco> barco = barcoRepo.findById(idBarco);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails && barco.isPresent()) {
            String nombre = ((UserDetails) principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);

            if (barco.isPresent() && socio.get().getBarcos().contains(barco.get())) {
                Salida salida1 = new Salida();
                salida1.setFechaHoraSalida(LocalDateTime.now());
                salida1.setDestino(salida.getDestino());
                salida1.setFechaHoraLlegada(null);
                salida1.setNombrePatron(salida.getNombrePatron());
                salida1.setBarco(barco.get());
                barco.get().getSalidas().add(salida1);
                barcoRepo.save(barco.get());
                return salida1;
            } else {
                throw new RuntimeException("No se encuentra el barco o no tienes barcos asociados ");
            }
        } else {
            return null;
        }
    }








    public List<Salida> listarSalida(){
        return salidaRepo.findAll();
    }

    public Salida findById(UUID id){
        Optional<Salida> salida = salidaRepo.findById(id);
        return salida.get();
    }

    public Salida editarSalida(UUID id,Salida salida){
        Optional<Salida> salida1 = salidaRepo.findById(id);


        if (salida1.isPresent()&& salida1.get().getFechaHoraLlegada() == null){
           return salida1.map(s->{
               s.setFechaHoraLlegada(LocalDateTime.now());
               return salidaRepo.save(s);
           }).orElse(null);
        }else {
            throw new RuntimeException("No se encuentra la saldia con el id: "+id);
        }

    }

    public void eliminarSalida(UUID id){
        Optional<Salida> salida = salidaRepo.findById(id);
        if (salida.isEmpty()){
            throw new RuntimeException("No se encuentra la salida con el id: "+id);
        }else {
            salidaRepo.delete(salida.get());
        }
    }
}
