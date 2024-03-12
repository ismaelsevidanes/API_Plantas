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
 * Clase de prueba unitaria para la clase Usuario.
 */

public class UsuarioTest {

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
     * Prueba para un objeto Usuario válido.
     */
    @Test
    void testUsuario_Valid() {
        Usuario usuario = new Usuario();
        usuario.setDni("12345678A");
        usuario.setNombre("Ismael");
        usuario.setApellidos("Sevidanes");
        usuario.setTelefono(123456789);
        usuario.setDireccion("Calle asa  nº 1");
        usuario.setEmail("ismael.sevidanes@example.com");
        usuario.setPassword("root");
        
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        assertTrue(violations.isEmpty());
    }
    
	/**
	 * Prueba para un objeto Usuario con dni no válido.
	 */
    @Test
    void testUsuario_InvalidDni() {
        	 Usuario usuario = new Usuario();
             usuario.setDni("");
             usuario.setNombre("Ismael");
             usuario.setApellidos("Sevidanes");
             usuario.setTelefono(123456789);
             usuario.setDireccion("Calle asa  nº 1");
             usuario.setEmail("ismael.sevidanes@example.com");
             usuario.setPassword("root");
             
             Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

             assertEquals(1, violations.size());
             ConstraintViolation<Usuario> violation = violations.iterator().next();
             assertEquals("El dni no puede estar en blanco", violation.getMessage());
         }
    
	/**
	 * Prueba para un objeto Usuario con nombre no válido.
	 */
    @Test
    void testUsuario_InvalidNombre() {
        	 Usuario usuario = new Usuario();
             usuario.setDni("12345678A");
             usuario.setNombre("");
             usuario.setApellidos("Sevidanes");
             usuario.setTelefono(123456789);
             usuario.setDireccion("Calle asa  nº 1");
             usuario.setEmail("ismael.sevidanes@example.com");
             usuario.setPassword("root");
             
             Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

             assertEquals(1, violations.size());
             ConstraintViolation<Usuario> violation = violations.iterator().next();
             assertEquals("El nombre no puede estar en blanco", violation.getMessage());
         }
    
        /**
		 * Prueba para un objeto Usuario con apellidos no válidos.
		 */
         @Test
         void testUsuario_InvalidApellido() {
             	 Usuario usuario = new Usuario();
                  usuario.setDni("12345678A");
                  usuario.setNombre("Ismael");
                  usuario.setApellidos("");
                  usuario.setTelefono(123456789);
                  usuario.setDireccion("Calle asa  nº 1");
                  usuario.setEmail("ismael.sevidanes@example.com");
                  usuario.setPassword("root");
                  
                  Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                  assertEquals(1, violations.size());
                  ConstraintViolation<Usuario> violation = violations.iterator().next();
                  assertEquals("El apellido no puede estar en blanco", violation.getMessage());
              }
         
         /**
 		 * Prueba para un objeto Usuario con telefono no válidos.
 		 */
          @Test
          void testUsuario_InvalidTelefono() {
              	 Usuario usuario = new Usuario();
                   usuario.setDni("12345678A");
                   usuario.setNombre("Ismael");
                   usuario.setApellidos("Sevidanes");
                   usuario.setTelefono(null);
                   usuario.setDireccion("Calle asa  nº 1");
                   usuario.setEmail("ismael.sevidanes@example.com");
                   usuario.setPassword("root");
                   
                   Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                   assertEquals(1, violations.size());
                   ConstraintViolation<Usuario> violation = violations.iterator().next();
                   assertEquals("El telefono no puede ser null", violation.getMessage());
               }
          
          /**
   		 * Prueba para un objeto Usuario con direccion no válidos.
   		 */
            @Test
            void testUsuario_InvalidDireccion() {
                	 Usuario usuario = new Usuario();
                     usuario.setDni("12345678A");
                     usuario.setNombre("Ismael");
                     usuario.setApellidos("Sevidanes");
                     usuario.setTelefono(123456789);
                     usuario.setDireccion("");
                     usuario.setEmail("ismael.sevidanes@example.com");
                     usuario.setPassword("root");
                     
                     Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                     assertEquals(1, violations.size());
                     ConstraintViolation<Usuario> violation = violations.iterator().next();
                     assertEquals("La direccion no puede estar en blanco", violation.getMessage());
                 }

            /**
       		 * Prueba para un objeto Usuario con direccion no válidos.
       		 */
                @Test
                void testUsuario_InvalidEmail() {
                    	 Usuario usuario = new Usuario();
                         usuario.setDni("12345678A");
                         usuario.setNombre("Ismael");
                         usuario.setApellidos("Sevidanes");
                         usuario.setTelefono(123456789);
                         usuario.setDireccion("calla asa nº 1");
                         usuario.setEmail("");
                         usuario.setPassword("root");
                         
                         Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                         assertEquals(1, violations.size());
                         ConstraintViolation<Usuario> violation = violations.iterator().next();
                         assertEquals("El email no puede estar en blanco", violation.getMessage());
                     }
                /**
           		 * Prueba para un objeto Usuario con direccion no válidos.
           		 */
                    @Test
                    void testUsuario_InvalidPassword() {
                        	 Usuario usuario = new Usuario();
                             usuario.setDni("12345678A");
                             usuario.setNombre("Ismael");
                             usuario.setApellidos("Sevidanes");
                             usuario.setTelefono(123456789);
                             usuario.setDireccion("calla asa nº 1");
                             usuario.setEmail("ismael.sevidanes@example.com");
                             usuario.setPassword("");
                             
                             Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

                             assertEquals(1, violations.size());
                             ConstraintViolation<Usuario> violation = violations.iterator().next();
                             assertEquals("La contraseña no puede estar en blanco", violation.getMessage());
                         }
    
    }
        		
        		