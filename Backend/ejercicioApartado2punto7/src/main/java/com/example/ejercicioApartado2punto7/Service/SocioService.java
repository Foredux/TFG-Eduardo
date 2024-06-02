package com.example.ejercicioApartado2punto7.Service;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Salida;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Repository.BarcoRepo;
import com.example.ejercicioApartado2punto7.Repository.SalidaRepo;
import com.example.ejercicioApartado2punto7.Repository.SocioRepo;
import com.example.ejercicioApartado2punto7.users.Dto.GetUsuario;
import com.example.ejercicioApartado2punto7.users.Dto.PostCrearUserDto;
import com.example.ejercicioApartado2punto7.users.Dto.PostLogin;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import com.example.ejercicioApartado2punto7.users.model.UserRoles;
import com.example.ejercicioApartado2punto7.users.repositorio.AdministradorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class
SocioService {

    private final SocioRepo socioRepo;
    private final BarcoRepo barcoRepo;
    private final SalidaRepo salidaRepo;
    private final SocioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdministradorRepo administradorRepo;
    private final EmailService emailService;

    public List<Socio> listarSocios(){
        return socioRepo.findAll();
    }

    public Socio findByNombre(String nombreSocio){
        Optional<Socio> socio= socioRepo.findByNameIgnoreCase(nombreSocio);
        return  socio.get();
    }








    public Optional<Socio> findById(UUID id){return usuarioRepo.findById(id);}

    public Optional<Socio> findByEmail(String email) {
        return usuarioRepo.findFirstByEmail(email);
    }

    public  Socio crearUsuario(PostCrearUserDto postCrearUserDto, EnumSet<UserRoles> userRoles){
        if (usuarioRepo.existsByEmailIgnoreCase(postCrearUserDto.email())||administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");
        }
        Socio usuario = Socio.builder()
                .email(postCrearUserDto.email())
                .name(postCrearUserDto.name())
                .saldo(1000.0)
                .fotoUrl(postCrearUserDto.fotoUrl())
                .lastName(postCrearUserDto.lastName())
                .username(postCrearUserDto.username())
                .password(passwordEncoder.encode(postCrearUserDto.password()))
                .createdAt(LocalDateTime.now())
                .phoneNumber(postCrearUserDto.phoneNumber())
                .birthDate(postCrearUserDto.nacimiento())
                .accountNonExpired(false)
                .roles(EnumSet.of(UserRoles.SOCIO))
                .enabled(false)
                .build();
        emailService.sendConfirmationEmail(usuario);
        return usuarioRepo.save(usuario);

    }

    public Socio createWithRole(PostCrearUserDto postCrearUserDto){
        return crearUsuario(postCrearUserDto,EnumSet.of(UserRoles.SOCIO));
    }
    public GetUsuario getUsuario(UUID uuid){
        GetUsuario usuario = usuarioRepo.getUsuario(uuid);
        return usuario;

    }
    public Socio setearEnabled(PostLogin postCrearUserDto){
        Optional<Socio> usuario = usuarioRepo.findByEmailIgnoreCase(postCrearUserDto.email());

        if (usuario.isPresent() || usuario.get().isEnabled()){
            usuario.get().setEnabled(true);
            return usuarioRepo.save(usuario.get());
        }else {
            throw new RuntimeException("No se encuentra el usuario");
        }
    }
    public  Socio confirmationToken(String email){
        Optional<Socio> socio = socioRepo.findFirstByEmail(email);
       if (socio.isPresent()){
           socio.get().setEnabled(true);
           return socioRepo.save(socio.get());

       }else {
           throw new RuntimeException("FAllo en");
       }
    }

    public Socio verBarcosAsiganadoAUnSocio(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);
            return socio.get();

        }

        return null;
    }

    public Socio detallesDeUnSocio(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);
            if (socio.isPresent()){
                socioRepo.detallesDeUnSocio();
            }


        }

        return null;
    }

    public Socio editarSocio (Socio socio2){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> socio = socioRepo.findByEmailIgnoreCase(nombre);
            if (socio.isPresent()){

                socio.map(s->{
                    s.setName(socio2.getName());
                    s.setLastName(socio2.getLastName());
                    s.setSaldo(socio2.getSaldo());
                    s.setFotoUrl(socio2.getFotoUrl());
                    s.setPhoneNumber(socio2.getPhoneNumber());
                    s.setUsername(socio2.getUsername());
                    return usuarioRepo.save(s);
                }).orElse(null);
            }


        }
        return null;


    }

    public Socio getLoguedSocio(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> administrador = usuarioRepo.findByEmailIgnoreCase(nombre);
            return administrador.get();

        }

        return null;
    }

    public Socio logout(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Socio> administrador = usuarioRepo.findByEmailIgnoreCase(nombre);
            administrador.get().setEnabled(false);
            return usuarioRepo.save(administrador.get());

        }

        return null;
    }




}
