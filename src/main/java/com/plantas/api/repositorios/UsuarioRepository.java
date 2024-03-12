package com.plantas.api.repositorios;

import java.util.Optional;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.plantas.api.entidades.Usuario;

/**
 * Interfaz que proporciona métodos de acceso a la base de datos para la entidad
 * Usuario. Utiliza Spring Data JPA y hereda de JpaRepository.
 */
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByDni(String dni);
    Boolean existsByDni(String dni);
    Optional<Usuario> findById(Long id);
    boolean existsById(Long id);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Optional<Usuario> deleteById(Long id);
 }
