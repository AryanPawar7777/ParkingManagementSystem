package com.parking.parkingslots.config;

import com.parking.parkingslots.model.ServiceCredential;
import com.parking.parkingslots.repository.ServiceCredentialRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class ServiceAuthFilter extends OncePerRequestFilter {

    @Autowired
    private ServiceCredentialRepository credentialRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Authorization Header");
            return;
        }

        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        String[] values = credentials.split(":", 2);

        if (values.length != 2) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization format");
            return;
        }

        String username = values[0];
        String password = values[1];

        Optional<ServiceCredential> credential = credentialRepository.findByUsername(username);

        if (credential.isEmpty() || !credential.get().getPassword().equals(password)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credentials");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
