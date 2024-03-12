package com.plantas.api.servicios;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.plantas.api.entidades.Producto;

/**
 * Interfaz que define los métodos para el servicio de gestión de productos.
 */

public interface ProductoService{
    Page<Producto> getAllProductos(Pageable pageable);
    Optional<Producto> getProductosById(Long id);
    Producto saveProductos(Producto producto);
    Boolean deleteProductos(Long id);
    Producto updateProductos(Long id, Producto producto);
    Page<Producto> filtrarProductos(String nombre, Integer precio, Pageable pageable);
    
}
