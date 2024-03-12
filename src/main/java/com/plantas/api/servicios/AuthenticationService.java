package com.plantas.api.servicios;

import com.plantas.api.dtos.requests.LoginRequest;
import com.plantas.api.dtos.requests.RegistroRequest;
import com.plantas.api.dtos.responses.JwtAuthenticationResponse;

/**
 * Interfaz que define los métodos para la autenticación de usuarios.
 */
public interface AuthenticationService {

    /**
     * Registra a un nuevo usuario y genera una respuesta de autenticación JWT.
     *
     * @param request  información del usuario a registrar.
     * @return La respuesta de autenticación JWT.
     */
    JwtAuthenticationResponse signup(RegistroRequest request);

    /**
     * Autentica a un usuario existente y genera una respuesta de autenticación JWT.
     *
     * @param request  información del usuario para iniciar sesión.
     * @return La respuesta de autenticación JWT.
     */
    JwtAuthenticationResponse signin(LoginRequest request);
}