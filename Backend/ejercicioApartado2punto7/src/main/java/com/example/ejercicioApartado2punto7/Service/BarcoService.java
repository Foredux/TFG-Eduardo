package com.example.ejercicioApartado2punto7.Service;

import com.example.ejercicioApartado2punto7.Dto.GetListBarcosNoOcupados;
import com.example.ejercicioApartado2punto7.Dto.GetListaBarcosDto;
import com.example.ejercicioApartado2punto7.Dto.PostEditarBarcoDto;
import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Repository.BarcoRepo;
import com.example.ejercicioApartado2punto7.Repository.SocioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BarcoService {
    private final SocioRepo socioRepo;
    private final BarcoRepo barcoRepo;
    public Barco crearBarco(Barco barco){
        Optional<Barco> barco2 = barcoRepo.findByMatricula(barco.getMatricula());
        if (barco2.isEmpty()){
            Barco barco1 = new Barco();
            barco1.setCuota(barco.getCuota());
            barco1.setMatricula(barco.getMatricula());
            barco1.setNombre(barco.getNombre());

            barco1.setNumeroAmarre(barco.getNumeroAmarre());
            barco1.setOcupado(false);
            barco1.setSocio(null);
            return barcoRepo.save(barco1);
        }else{

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");

        }

    }
    public List<GetListBarcosNoOcupados> BuscarPorNombreOPorMatricula(String busqueda){
        List<Barco> barcosPorNombre = barcoRepo.findAllByNombreIgnoreCase(busqueda);
        Optional<Barco> barcoPorMatricula = barcoRepo.findByMatricula(busqueda);

        if (!barcosPorNombre.isEmpty()){
            return barcosPorNombre.stream()
                    .map(GetListBarcosNoOcupados::of)
                    .toList();
        } else if (barcoPorMatricula.isPresent()){
            return List.of(GetListBarcosNoOcupados.of(barcoPorMatricula.get()));
        } else {
            return null;
        }
    }




    public Barco findByMatricula (String matricula){
      Optional<Barco> barco = barcoRepo.findByMatricula(matricula);
      if (barco.isEmpty()){
          throw new RuntimeException("No se encuentra el barco con matricula: "+matricula);
      }else {
          return barco.get();
      }

    }
    public List<Barco> listarBarcosNoOcupados(){
        return barcoRepo.listarBarcosNoOcupados();
    }

    public List<GetListBarcosNoOcupados> listarBarcosNoOcupadosAdmin(){
        return barcoRepo.findAll().stream().filter(b-> !b.isOcupado()).map(GetListBarcosNoOcupados::of).toList();
    }
    public List<GetListBarcosNoOcupados> listarBarcosNoOcupadosSocio(){
        return barcoRepo.findAll().stream().filter(b-> !b.isOcupado()).map(GetListBarcosNoOcupados::of).toList();
    }
    public List<GetListBarcosNoOcupados> listarBarcosOcupadosAdmin(){
        return barcoRepo.findAll().stream().filter(b-> b.isOcupado()).map(GetListBarcosNoOcupados::of).toList();
    }

    public List<GetListaBarcosDto> listadoDeBarcos(){
        return barcoRepo.findAll().stream().map(GetListaBarcosDto::of).toList();
    }


    public Barco asignarBarco(UUID uuidBarco){

        Optional<Barco> barco = barcoRepo.findById(uuidBarco);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails && barco.isPresent()) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);
            if (socio.get().getSaldo()>=barco.get().getCuota()){
                double total = 0.0;
                total =socio.get().getSaldo() - barco.get().getCuota();
                socio.get().setSaldo(total);
                barco.get().setOcupado(true);
                barco.get().setSocio(socio.get());
                socioRepo.save(socio.get());
                return barcoRepo.save(barco.get());
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No tienes suficiente saldo");
            }





        }else {
            return null;
        }


    }

    public Barco desasignarBarco(UUID barcoId){
        Optional<Barco> barco = barcoRepo.findById(barcoId);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails && barco.isPresent()) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);
            if (socio.get().getSaldo()>=barco.get().getCuota()){
                double total = 0.0;
                total =socio.get().getSaldo() + barco.get().getCuota();
                socio.get().setSaldo(total);
                barco.get().setOcupado(false);
                barco.get().setSocio(null);
                socioRepo.save(socio.get());
                return barcoRepo.save(barco.get());
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No tienes suficiente saldo");
            }





        }else {
            return null;
        }
    }

    public Barco editarBarco(UUID id, PostEditarBarcoDto barco){
        Optional<Barco> barco1 = barcoRepo.findById(id);
        Optional<Barco> barco2 = barcoRepo.findByMatricula(barco.matricula());
        if (barco2.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");
        }

        if (barco1.isEmpty()){
            throw new RuntimeException("No se encuentra el barco con la matricula: ");
        }else{
          return   barco1.map(b->{
                b.setCuota(barco.cuota());
                b.setNombre(barco.nombre());
                b.setMatricula(barco.matricula());
                b.setNumeroAmarre(barco.numeroAmarre());
                return barcoRepo.save(b);
            }).orElse(null);

        }
    }

    public void eliminarBarco(UUID uuid){
        Optional<Barco> barco = barcoRepo.findById(uuid);
        if (barco.isEmpty()){
            throw new RuntimeException("No se encuentra el barco con matricula: "+uuid);
        }else {
            barcoRepo.delete(barco.get());
        }
    }
}
