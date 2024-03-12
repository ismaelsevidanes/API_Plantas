package com.plantas.api.dtos;

/**
 * DTO (Data Transfer Object) representa un usuario. Se utiliza para
 * transferir información de la aplicación.
 */
public class UsuarioDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String rol;
    
    public UsuarioDTO() {

    }
	
    
	/**
	 * Constructor que toma los atributos del usuario.
	 *
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param rol
	 */
    public UsuarioDTO(String nombre, String apellidos, String email, String rol) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
    }
    
    public String getNombre() {
        return nombre;
    }
	
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
	
	
}
