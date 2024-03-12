package com.plantas.api.servicios;


import java.util.List;

import java.util.Optional;

import com.plantas.api.dtos.UsuarioDTO;
import com.plantas.api.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interfaz que define los métodos para el servicio de usuarios.
 */

	public interface UsuarioService {
	
	    /**
	     * Obtiene el servicio de detalles de usuario.
	     *
	     */
	    UserDetailsService userDetailsService();
	
	    /**
	     * Obtiene una lista de todos los usuarios como objetos DTO.
	     *
	     * @return Lista de objetos UsuarioDTO.
	     */
	    List<UsuarioDTO> obtenerTodos();
	    
	    Page<Usuario> getAllUsuario(Pageable pageable);
	    Optional<Usuario> getUsuarioById(Long id);
	    Usuario saveUsuario(Usuario venta);
	    Boolean deleteUsuario(Long id);
	    Usuario updateUsuario(Long id, Usuario venta);
	    Page<Usuario> filtrarUsuario(String fecha, Pageable pageable);
	
	}
