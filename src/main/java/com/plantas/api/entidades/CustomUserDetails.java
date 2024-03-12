package com.plantas.api.entidades;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * La clase CustomUserDetails implementa la interfaz UserDetails de Spring Security
 * para proporcionar detalles específicos de un usuario personalizado.
 */
public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;


    private String nombre;
    private String password;

    /**
     * Constructor de la clase CustomUserDetails.
     */
    public CustomUserDetails(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    /**
     * Devuelve una colección de autoridades concedidas al usuario.
     *
     * @return Colección de autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
    	return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}