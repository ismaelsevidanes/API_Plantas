package com.plantas.api.repositorios;

import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.plantas.api.entidades.Venta;

/**
 * Interfaz que proporciona métodos de acceso a la base de datos para la entidad
 * Venta. Utiliza Spring Data JPA y hereda de JpaRepository.
 */
public interface VentaRepository extends JpaRepository<Venta, Integer> {
	Optional<Venta> findById(Long id);
	boolean existsById(Long id);
	Page<Venta> findByFechaContainingIgnoreCase(String fecha, Pageable pageable);
	Optional<Venta> deleteById(Long id);
	Page<Venta> findByUsuarioId(Integer usuarioId, Pageable pageable);
	Page<Venta> findByProductoId(Integer productoId, Pageable pageable);
}