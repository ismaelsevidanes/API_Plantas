package com.plantas.api.serviciosImpl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import com.plantas.api.dtos.UsuarioDTO;
import com.plantas.api.entidades.Usuario;
import com.plantas.api.repositorios.UsuarioRepository;
import com.plantas.api.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de usuario que proporciona operaciones
 * relacionadas con usuarios.
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	/**
	 * Inyección del repositorio de usuario.
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Retorna un objeto UserDetailsService que carga los detalles de usuario por
	 * nombre de usuario.
	 *
	 * @return Un objeto UserDetailsService para cargar los detalles de usuario.
	 */
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String nombre) {
				return usuarioRepository.findByEmail(nombre)
						.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			}
		};
	}

	/**
	 * Obtiene todos los usuarios y los convierte en una lista de objetos
	 * UsuarioDTO.
	 *
	 * @return Una lista de todos los usuarios convertidos en objetos UsuarioDTO.
	 */
	@Override
	public List<UsuarioDTO> obtenerTodos() {
		List<UsuarioDTO> usuarios = usuarioRepository.findAll().stream()
				.map(usuario -> new UsuarioDTO(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(),
						usuario.getRoles().toString()))
				.collect(Collectors.toList());
		return usuarios;
	}

	@Override
	public Page<Usuario> getAllUsuario(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Usuario> getUsuarioById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario saveUsuario(Usuario venta) {
		return usuarioRepository.save(venta);
	}

	@Override
	public Boolean deleteUsuario(Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return null;
	}

	@Override
	public Usuario updateUsuario(Long id, Usuario venta) {
		Optional<Usuario> ventaById = usuarioRepository.findById(id);
		
		if (ventaById.isPresent()) {
			Usuario updateVenta = ventaById.get();

			updateVenta.setId(venta.getId());
			updateVenta.setNombre(venta.getNombre());
			updateVenta.setApellidos(venta.getApellidos());
			updateVenta.setDni(venta.getDni());
			updateVenta.setEmail(venta.getEmail());
			updateVenta.setRoles(venta.getRoles());
			return usuarioRepository.save(updateVenta);
		}
		return null;
	}

	@Override
	public Page<Usuario> filtrarUsuario(String nombre, Pageable pageable) {
		return usuarioRepository.findByNombreContainingIgnoreCase(nombre, pageable);
	}
}