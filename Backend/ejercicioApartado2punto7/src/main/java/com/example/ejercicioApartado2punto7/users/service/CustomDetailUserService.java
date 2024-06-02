package com.example.ejercicioApartado2punto7.users.service;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Service.SocioService;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Primary
@Service("patientDetailsService")
@RequiredArgsConstructor
public class CustomDetailUserService implements UserDetailsService {

    private final SocioService patientService;
    private final AdministradorService sanitaryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Socio> usuario = patientService.findByEmail(email);
        Optional<Administrador> administrador = sanitaryService.findByEmail(email);

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            if (administrador.isPresent()) {
                return administrador.get();
            } else {
                throw (new UsernameNotFoundException("No user with email: " + email));
            }
        }

    }
}
