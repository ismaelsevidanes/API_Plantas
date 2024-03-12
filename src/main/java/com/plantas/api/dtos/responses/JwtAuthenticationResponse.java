package com.plantas.api.dtos.responses;

/**
 * DTO (Data Transfer Object) representa una respuesta de autenticación JWT.
 * Se utiliza para transferir información de la aplicación.
 */
public class JwtAuthenticationResponse {
	
    private String token;

	public JwtAuthenticationResponse() {

	}
	
	/**
	 * Constructor que toma un token JWT.
	 *
	 * @param token El token JWT generado como respuesta de autenticación.
	 */
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
	/**
	 * Creador de instancias para construir objetos JwtAuthenticationResponse de
	 * manera fluida.
	 *
	 * @return Instancia de JwtAuthenticationResponseBuilder para construir objetos
	 *         JwtAuthenticationResponse.
	 */
	public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }
	/**
	 * Clase interna que actúa como constructor de JwtAuthenticationResponse para
	 * construcción fluida.
	 */
    public static class JwtAuthenticationResponseBuilder {
        private String token;

        private JwtAuthenticationResponseBuilder() {
        }

		/**
		 * Establece el token JWT.
		 *
		 * @param token El token JWT.
		 * @return El constructor para continuar con la construcción fluida.
		 */
        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

       /**
       * Construye un objeto JwtAuthenticationResponse con los valores establecidos.
       * @return Un objeto JwtAuthenticationResponse.
       */
        public JwtAuthenticationResponse build() {
            return new JwtAuthenticationResponse(token);
        }
    }
}
