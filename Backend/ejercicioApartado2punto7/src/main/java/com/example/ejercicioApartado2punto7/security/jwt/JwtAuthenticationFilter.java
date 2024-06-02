package com.example.ejercicioApartado2punto7.security.jwt;
import com.example.ejercicioApartado2punto7.Model.Socio;
import com.example.ejercicioApartado2punto7.Service.SocioService;
import com.example.ejercicioApartado2punto7.security.errorhandling.JwtTokenException;
import com.example.ejercicioApartado2punto7.users.model.Administrador;
import com.example.ejercicioApartado2punto7.users.service.AdministradorService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final SocioService usuarioService;

    @Autowired
    private final AdministradorService administradorService;

    @Autowired
    private final JwtProvider jwtProvider;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtTokenFromRequest(request);

        try {
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
                UUID userId = jwtProvider.getUserIdFromJwtToken(token);

                Optional<Socio> result = usuarioService.findById(userId);


                if (result.isPresent()) {
                    Socio patient = result.get();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    patient,
                                    null,
                                    patient.getAuthorities()
                            );

                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    Optional<Administrador> resultSanitary = administradorService.findById(userId);

                    if(resultSanitary.isPresent()){

                        Administrador sanitary = resultSanitary.get();
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        sanitary,
                                        null,
                                        sanitary.getAuthorities()
                                );

                        authentication.setDetails(new WebAuthenticationDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }
                }

            }

            filterChain.doFilter(request, response);

        } catch (JwtTokenException ex) {
            log.info("Authentication error using token JWT: " + ex.getMessage());
            resolver.resolveException(request, response, null, ex);
        }
    }


    private String getJwtTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)) {
            return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
        }
        return null;
    }
}