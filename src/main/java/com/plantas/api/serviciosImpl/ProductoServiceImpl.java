package com.plantas.api.serviciosImpl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plantas.api.entidades.Producto;
import com.plantas.api.repositorios.ProductoRepository;
import com.plantas.api.servicios.ProductoService;


/**
 * Implementación del servicio para operaciones relacionadas con la entidad
 * Producto.
 */
@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

	@Override
	public Page<Producto> getAllProductos(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	@Override
	public Optional<Producto> getProductosById(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public Producto saveProductos(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Boolean deleteProductos(Long id) {
		if (productoRepository.existsById(id)) {
			productoRepository.deleteById(id);
			return true;
		}
		return null;
	}

	@Override
	public Producto updateProductos(Long id, Producto producto) {
		  Optional<Producto> productoById = productoRepository.findById(id);

	        if (productoById.isPresent()) {
	            Producto updateProducto = productoById.get();

	            updateProducto.setId(producto.getId());
	            updateProducto.setNombre(producto.getNombre());
	            updateProducto.setDescripcion(producto.getDescripcion());
	            updateProducto.setStock(producto.getStock());
	            updateProducto.setPrecio(producto.getPrecio());

	            return productoRepository.save(updateProducto);
	        }
		return null;
	}

	@Override
	public Page<Producto> filtrarProductos(String nombre, Integer precio, Pageable pageable) {
	    if (nombre == null && precio == null) {
            throw new IllegalArgumentException("Debe proporcionar al menos un criterio de filtrado");
        }

        if (nombre != null) {
            return filtrarPorNombre(nombre, pageable);
        } else if (precio != null) {
            return filtrarPorApellido(precio, pageable);
        } 
        return null;
	}
    
	 private Page<Producto> filtrarPorNombre(String nombre, Pageable pageable) {
	        return productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);
	    }

		private Page<Producto> filtrarPorApellido(Integer precio, Pageable pageable) {
			return productoRepository.findByPrecio(precio, pageable);
		}
	
	
	
}