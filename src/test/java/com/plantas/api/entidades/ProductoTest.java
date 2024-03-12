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
 * Clase de prueba unitaria para la clase Producto.
 */

public class ProductoTest {

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
	 * Prueba para un objeto Producto válido.
	 */
	@Test
	void testProducto_Valid() {
		Producto producto = new Producto();
		producto.setNombre("Planta");
		producto.setDescripcion("Planta de interior");
		producto.setPrecio(10);
		producto.setStock(10);

		Set<ConstraintViolation<Producto>> violations = validator.validate(producto);
		assertTrue(violations.isEmpty());
	}
	
	/**
	 * Prueba para un objeto Producto con nombre no válido.
	 */
	@Test
	void testProducto_InvalidNombre() {
		Producto producto = new Producto();
		producto.setNombre("");
		producto.setDescripcion("Planta de interior");
		producto.setPrecio(10);
		producto.setStock(10);

		Set<ConstraintViolation<Producto>> violations = validator.validate(producto);

        assertEquals(1, violations.size());
        ConstraintViolation<Producto> violation = violations.iterator().next();
        assertEquals("El nombre no puede estar en blanco", violation.getMessage());
	}
	
	/**
	 * Prueba para un objeto Producto con descripción no válida.
	 */
	@Test
	void testProducto_InvalidDescripcion() {
		Producto producto = new Producto();
		producto.setNombre("Planta");
		producto.setDescripcion("");
		producto.setPrecio(10);
		producto.setStock(10);

		Set<ConstraintViolation<Producto>> violations = validator.validate(producto);

        assertEquals(1, violations.size());
        ConstraintViolation<Producto> violation = violations.iterator().next();
        assertEquals("La descripcion no puede estar en blanco", violation.getMessage());
	}
	
	/**
	 * Prueba para un objeto Producto con precio no válido.
	 */
	@Test
	void testProducto_InvalidPrecio() {
		Producto producto = new Producto();
		producto.setNombre("Planta");
		producto.setDescripcion("Planta de interior");
		producto.setPrecio(null);
		producto.setStock(10);

		Set<ConstraintViolation<Producto>> violations = validator.validate(producto);

        assertEquals(1, violations.size());
        ConstraintViolation<Producto> violation = violations.iterator().next();
        assertEquals("El precio no puede estar en blanco o null", violation.getMessage());
	}
	
	/**
	 * Prueba para un objeto Producto con stock no válido.
	 */
	@Test
	
	void testProducto_InvalidStock() {
		Producto producto = new Producto();
		producto.setNombre("Planta");
		producto.setDescripcion("Planta de interior");
		producto.setPrecio(10);
		producto.setStock(null);

		Set<ConstraintViolation<Producto>> violations = validator.validate(producto);

        assertEquals(1, violations.size());
        ConstraintViolation<Producto> violation = violations.iterator().next();
        assertEquals("El stock no puede ser null", violation.getMessage());
	}
}