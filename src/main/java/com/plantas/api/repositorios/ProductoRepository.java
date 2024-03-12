package com.plantas.api.repositorios;

import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.plantas.api.entidades.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Integer>{
    Optional<Producto> findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
    Optional<Producto> findById(Long id);
    boolean existsById(Long id);
    
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Page<Producto> findByPrecio(Integer precio, Pageable pageable);
    Optional<Producto> deleteById(Long id);


 }