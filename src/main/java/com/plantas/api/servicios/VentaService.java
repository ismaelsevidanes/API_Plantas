package com.plantas.api.servicios;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.plantas.api.entidades.Venta;

/**
 * Interfaz que define los métodos para el servicio de gestión de ventas.
 */

	public interface VentaService{
	    Page<Venta> getAllVentas(Pageable pageable);
	    Optional<Venta> getVentasById(Long id);
	    Venta saveVentas(Venta venta);
	    Boolean deleteVentas(Long id);
	    Venta updateVentas(Long id, Venta venta);
	    Page<Venta> filtrarVentas(String fecha, Pageable pageable);
	}