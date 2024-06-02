package com.example.ejercicioApartado2punto7.Controller;


import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Service.SocioService;
import com.example.ejercicioApartado2punto7.security.jwt.JwtProvider;
import com.example.ejercicioApartado2punto7.users.Dto.*;
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

public class SocioController {

    private final SocioService socioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("auth/register/user")
    public ResponseEntity<?> crearUser(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Socio usuario = socioService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Usuario(usuario));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @PostMapping("/auth/login/user")
    public ResponseEntity<JwtUserResponse> loginUser(@RequestBody PostLogin postLogin){
       // socioService.setearEnabled(postLogin);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Socio usuario = (Socio) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofUsuario(usuario, token));
    }
    @GetMapping("/confirm-account/{email}")
    public String confirmAccount(@PathVariable String email) {
        // El token vendrá con el prefijo "Bearer ", debes eliminarlo para obtener el token puro


        Socio user = socioService.confirmationToken(email);
        return "Email confirmado";


}
    @GetMapping("usuario/ver/detalles/{id}")
    public ResponseEntity<GetUsuario> verDetallesUsuario(@PathVariable UUID id){
        GetUsuario usuario1 = socioService.getUsuario(id);
        return ResponseEntity.ok(usuario1);
    }

    @GetMapping("/socio/ver/barcos/asiganados")
    public ResponseEntity<Socio> verBarcosAsiganados(){
        Socio socio = socioService.verBarcosAsiganadoAUnSocio();
        return ResponseEntity.ok(socio);
    }

    @GetMapping("/socio/detalles")
    public ResponseEntity<Socio> detallesDeUnSocio(){
        Socio socio = socioService.detallesDeUnSocio();
        return ResponseEntity.ok(socio);
    }

    @PutMapping("socio/editar")
    public ResponseEntity<Socio> editarSocio(@RequestBody Socio socio){
        Socio socio1 = socioService.editarSocio(socio);
        return ResponseEntity.status(201).body(socio1);
    }

    @GetMapping("socio/logueado")
    public ResponseEntity<Socio> verSocioLogueado(){
        Socio socio = socioService.getLoguedSocio();
        return ResponseEntity.ok(socio);
    }

    @PostMapping("socio/logout")
    public ResponseEntity<Socio> logoutSocio(){
        Socio socio = socioService.logout();
        return ResponseEntity.status(201).body(socio);
    }








}
