package com.example.ejercicioApartado2punto7.config;



import com.example.ejercicioApartado2punto7.Model.Socio;
import lombok.extern.java.Log;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.*;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Log
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof Socio)
                .map(Socio.class::cast)
                .map(Socio::getId)
                .map(java.util.UUID::toString);


    }
}
