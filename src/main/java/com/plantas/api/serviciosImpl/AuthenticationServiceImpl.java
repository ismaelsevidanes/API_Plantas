package com.plantas.api.serviciosImpl;

import lombok.Builder;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plantas.api.servicios.AuthenticationService;
import com.plantas.api.repositorios.UsuarioRepository;
import com.plantas.api.servicios.JwtService;
import com.plantas.api.dtos.requests.LoginRequest;
import com.plantas.api.dtos.requests.RegistroRequest;
import com.plantas.api.dtos.responses.JwtAuthenticationResponse;
import com.plantas.api.entidades.Role;
import com.plantas.api.entidades.Usuario;


/**
 * Implementación del servicio de autenticación que proporciona funcionalidades
 * para registro y login de usuarios.
 */
@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    
	/**
	 * Constructor de la clase AuthenticationServiceImpl.
	 *
	 * @param usuarioRepository
	 * @param passwordEncoder
	 * @param jwtService
	 * @param authenticationManager
	 */
	public AuthenticationServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	  /**
     * Registra a un nuevo usuario en el sistema.
     *
     * @param request
     * @return Objeto JwtAuthenticationResponse con el token de autenticación generado.
     * @throws IllegalArgumentException Si el email proporcionado ya está en uso.
     */
    @Override
    public JwtAuthenticationResponse signup(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email ya está en uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.getRoles().add(Role.ROLE_USER);
        usuarioRepository.save(usuario);
        String jwt = jwtService.generateToken(usuario);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Inicia sesión para un usuario existente.
     *
     * @param request
     * @return Objeto JwtAuthenticationResponse con el token de autenticación generado.
     * @throws IllegalArgumentException Si el email o la contraseña proporcionados son inválidos.
     */
    @Override
    public JwtAuthenticationResponse signin(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<Usuario> optionalUser = usuarioRepository.findByEmail(request.getEmail());

        Usuario usuario = optionalUser.orElseThrow(() -> new IllegalArgumentException("Email o contraseña inválidos."));
        String jwt = jwtService.generateToken(usuario);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
    
	
}
