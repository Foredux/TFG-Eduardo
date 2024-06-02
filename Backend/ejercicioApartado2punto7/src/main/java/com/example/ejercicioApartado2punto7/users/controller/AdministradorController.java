package com.example.ejercicioApartado2punto7.users.controller;

import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Service.SocioService;
import com.example.ejercicioApartado2punto7.security.jwt.JwtProvider;
import com.example.ejercicioApartado2punto7.users.Dto.JwtUserResponse;
import com.example.ejercicioApartado2punto7.users.Dto.PostCrearUserDto;
import com.example.ejercicioApartado2punto7.users.Dto.PostLogin;
import com.example.ejercicioApartado2punto7.users.Dto.PostRegistroDto;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import com.example.ejercicioApartado2punto7.users.service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService administradorService;
    private final SocioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @PostMapping("auth/register/admin")
    public ResponseEntity<?> crearAdministrador(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Administrador administrador = administradorService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Administrador(administrador));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PostMapping("auth/login/admin")
    public ResponseEntity<JwtUserResponse> loginadmin(@RequestBody PostLogin postLogin){
        administradorService.setearEnabled(postLogin);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Administrador administrador = (Administrador) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofAdminsitrador(administrador, token));
    }

    @GetMapping("administrador/ver/socios")
    public ResponseEntity<List<Socio>> listarUsuario(){
        List<Socio> usuarios = administradorService.listadoUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("administrador/buscar/socio/{nombre}")
    public ResponseEntity<Socio> buscaarSocioPorNombre(@PathVariable String nombre){
        Socio socio = administradorService.findByNombre(nombre);
        return ResponseEntity.ok(socio);
    }

    @GetMapping("administrador/logueado")
    public ResponseEntity<Administrador> getLoggedAdministrador() {
        Administrador administrador = administradorService.getLoggedAdministrador();
        return ResponseEntity.ok(administrador);
    }

    @PostMapping("administrador/logout")
    public ResponseEntity<Administrador> logOut(){
        Administrador administrador = administradorService.logOut();
        return ResponseEntity.status(201).body(administrador);
    }

    @DeleteMapping("administrador/eliminar/socio/{idSocio}")
    public ResponseEntity<?> eliminarSocio(@PathVariable UUID idSocio){
        administradorService.eliminarSocio(idSocio);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("administrador/editar")
    public ResponseEntity<Administrador> editarAdministrador(@RequestBody PostCrearUserDto postCrearUserDto){
        Administrador administrador = administradorService.editarPerfil(postCrearUserDto);
        return ResponseEntity.status(201).body(administrador);
    }

}
