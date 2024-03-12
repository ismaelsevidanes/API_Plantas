package com.plantas.api.controlador;

import com.plantas.api.dtos.requests.LoginRequest;

import com.plantas.api.dtos.requests.RegistroRequest;
import com.plantas.api.dtos.responses.JwtAuthenticationResponse;
import com.plantas.api.servicios.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador REST que gestiona las operaciones de autenticación y registro de
 * usuarios. Proporciona endpoints para registrar nuevos usuarios y para iniciar
 * sesión.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
	AuthenticationService authenticationService;

	/**
	 * Registra un nuevo usuario en el sistema.
	 */
	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody RegistroRequest request) {
		JwtAuthenticationResponse response = authenticationService.signup(request);
		return ResponseEntity.ok(response);
	}

	/**
	 * Inicia sesión para un usuario existente en el sistema.
	 */
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginRequest request) {
		JwtAuthenticationResponse response = authenticationService.signin(request);
		return ResponseEntity.ok(response);
	}
}