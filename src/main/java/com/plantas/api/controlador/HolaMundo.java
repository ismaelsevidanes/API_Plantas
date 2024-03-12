package com.plantas.api.controlador;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador REST que proporciona un endpoint para obtener un Hola mundo para comprobar la funcionalidad.
 */
@RestController
public class HolaMundo {

	@GetMapping("/")
	public String Holamundo() {
		return "Hola Mundo!";
	}
}