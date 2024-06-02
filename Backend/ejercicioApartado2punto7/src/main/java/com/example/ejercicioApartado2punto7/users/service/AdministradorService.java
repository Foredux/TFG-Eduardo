package com.example.ejercicioApartado2punto7.users.service;

import com.example.ejercicioApartado2punto7.Model.Barco;
import com.example.ejercicioApartado2punto7.Model.Salida;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Repository.BarcoRepo;
import com.example.ejercicioApartado2punto7.Repository.SalidaRepo;
import com.example.ejercicioApartado2punto7.Repository.SocioRepo;
import com.example.ejercicioApartado2punto7.Service.EmailService;
import com.example.ejercicioApartado2punto7.users.Dto.PostCrearUserDto;
import com.example.ejercicioApartado2punto7.users.Dto.PostLogin;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import com.example.ejercicioApartado2punto7.users.model.UserRoles;
import com.example.ejercicioApartado2punto7.users.repositorio.AdministradorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepo administradorRepo;
    private final PasswordEncoder passwordEncoder;
    private final SocioRepo usuarioRepo;
    private final SalidaRepo salidaRepo;
    private final BarcoRepo barcoRepo;
    private final EmailService emailService;
    public Optional<Administrador> findById(UUID id){return administradorRepo.findById(id);}
    public Optional<Administrador> findByEmail(String email) {
        return administradorRepo.findFirstByEmail(email);
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public  String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            builder.append(CHARACTERS.charAt(randomIndex));
        }

        return builder.toString();
    }
    public Administrador crearAdministrador(PostCrearUserDto postCrearUserDto , EnumSet<UserRoles> userRoles){
        String randomString = generateRandomString(10);
        if (usuarioRepo.existsByEmailIgnoreCase(postCrearUserDto.email())||administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");
        }
        Administrador administrador = Administrador.builder()
                .email(postCrearUserDto.email())
                .name(postCrearUserDto.name())
                .lastName(postCrearUserDto.lastName())
                .password(passwordEncoder.encode(randomString))
                .createdAt(LocalDateTime.now())
                .fotoUrl(postCrearUserDto.fotoUrl())
                .phoneNumber(postCrearUserDto.phoneNumber())
                .birthDate(postCrearUserDto.nacimiento())
                .roles(EnumSet.of(UserRoles.ADMINISTRADOR))
                .build();
        emailService.registroAdmin(administrador,randomString);
        return administradorRepo.save(administrador);
    }

    public Administrador createWithRole(PostCrearUserDto postCrearUserDto){
        return crearAdministrador(postCrearUserDto,EnumSet.of(UserRoles.ADMINISTRADOR));
    }
    public List<Socio> usuariosRegistrados(){
        List<Socio> usuarios = usuarioRepo.findByEnabledFalse();
        if (usuarios.isEmpty()){
            throw new RuntimeException("No se ha encontrados usuarios que se hayan registrados");
        }else {
            return usuarioRepo.findByEnabledFalse();
        }

    }

    public Socio setearEneable (UUID usuarioId){
        Optional<Socio> usuario = usuarioRepo.findById(usuarioId);
        if (usuario.isPresent()){
            Socio usuario1 = usuario.get();
            usuario1.setEnabled(false);
            return    usuarioRepo.save(usuario1);
        }else {
            throw new RuntimeException("Usuario con email: '"+usuarioId+"' no encontrado");
        }
    }

    public Administrador getLoggedAdministrador() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Administrador> administrador = administradorRepo.findByEmailIgnoreCase(nombre);
            return administrador.get();

        }

        return null;
    }

    public List<Socio> listadoUsuarios(){
        return usuarioRepo.findByEnabledTrue();
    }

    public Administrador logOut(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Administrador> administrador = administradorRepo.findByEmailIgnoreCase(nombre);
            administrador.get().setEnabled(false);
            return administradorRepo.save(administrador.get());

        }

        return null;
    }

    public Administrador setearEnabled(PostLogin postCrearUserDto){
        Optional<Administrador> administrador = administradorRepo.findByEmailIgnoreCase(postCrearUserDto.email());

        if (administrador.isPresent() || administrador.get().isEnabled()){
            administrador.get().setEnabled(true);
            return administradorRepo.save(administrador.get());
        }else {
            throw new RuntimeException("No se encuentra el administrador");
        }
    }

    public Socio findByNombre(String nombreSocio){
        Optional<Socio> socio= usuarioRepo.findByNameIgnoreCase(nombreSocio);
        return  socio.get();
    }

    public void eliminarSocio (UUID idSocio){
        Optional<Socio> socio= usuarioRepo.findById(idSocio);
        if (socio.isEmpty()){
            throw new RuntimeException("No se encuentra a ningun socio por el nombre de :");
        }else {
            Socio socioExistente = socio.get();
            List<Barco> barcos = socioExistente.getBarcos();
            barcos.forEach(barco -> {
                List<Salida> salidas = salidaRepo.findByBarco(barco);
                salidas.forEach(salida -> salidaRepo.delete(salida));
                barcoRepo.delete(barco);
            });

            usuarioRepo.delete(socioExistente);
        }
    }

    public Administrador editarPerfil(PostCrearUserDto postCrearUserDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Administrador> administrador = administradorRepo.findByEmailIgnoreCase(nombre);
            administrador.get().setName(postCrearUserDto.name());
            administrador.get().setFotoUrl(postCrearUserDto.fotoUrl());
            administrador.get().setLastName(postCrearUserDto.lastName());

            return administradorRepo.save(administrador.get());

        }

        return null;
    }

}
