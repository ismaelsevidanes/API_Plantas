package com.plantas.api.serviciosImpl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.plantas.api.entidades.Venta;
import com.plantas.api.repositorios.VentaRepository;
import com.plantas.api.servicios.VentaService;

/**
 * Implementación del servicio para operaciones relacionadas con la entidad
 * Venta.
 */
@Service

public class VentaServiceImpl implements VentaService {
	@Autowired
	VentaRepository ventaRepository;

	@Override
	public Page<Venta> getAllVentas(Pageable pageable) {
		return ventaRepository.findAll(pageable);
	}

	@Override
        public Optional<Venta> getVentasById(Long id) {
            return ventaRepository.findById(id);
        }
	
	@Override
	public Venta saveVentas(Venta venta) {
        return ventaRepository.save(venta);
    }
	
	@Override
	public Boolean deleteVentas(Long id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return null;
    }
	
	@Override
    public Venta updateVentas(Long id, Venta venta) {
          Optional<Venta> ventaById = ventaRepository.findById(id);

            if (ventaById.isPresent()) {
                Venta updateVenta = ventaById.get();

                updateVenta.setId(venta.getId());
                updateVenta.setFecha(venta.getFecha());
                updateVenta.setUsuario(venta.getUsuario());
                updateVenta.setProducto(venta.getProducto());
                return ventaRepository.save(updateVenta);
            }
            return null;
        }
	
	@Override
	public Page<Venta> filtrarVentas(String fecha, Pageable pageable) {
        return ventaRepository.findByFechaContainingIgnoreCase(fecha, pageable);
    }
	
	}