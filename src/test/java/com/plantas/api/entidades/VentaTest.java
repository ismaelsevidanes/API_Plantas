package com.plantas.api.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Clase de prueba unitaria para la clase Venta.
 */
public class VentaTest {
	
	private static Validator validator;

	/**
	 * Configura el validador antes de ejecutar las pruebas.
	 */
	@BeforeAll
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Prueba para un objeto Venta válido.
	 */
	@Test
	void testVenta_Valid() {
		Venta venta = new Venta();
		venta.setUsuario(new Usuario());
		venta.setFecha("2021-01-01");
		venta.setProducto(new Producto());

		Set<ConstraintViolation<Venta>> violations = validator.validate(venta);
		assertTrue(violations.isEmpty());
	}
	

	/**
	 * Prueba para un objeto Venta con fecha no válida.
	 */
	@Test
	void testVenta_InvalidFecha() {
		Venta venta = new Venta();
		venta.setUsuario(new Usuario());
		venta.setFecha("");
		venta.setProducto(new Producto());

		Set<ConstraintViolation<Venta>> violations = validator.validate(venta);

        assertEquals(1, violations.size());
        ConstraintViolation<Venta> violation = violations.iterator().next();
        assertEquals("La fecha no puede estar en blanco", violation.getMessage());
	}


}