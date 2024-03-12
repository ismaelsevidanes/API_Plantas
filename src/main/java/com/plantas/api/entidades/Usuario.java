package com.plantas.api.entidades;

import java.util.Collection;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.plantas.api.entidades.Role;
import jakarta.persistence.CollectionTable;
// imports
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
//import lombok.Getter;
//import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// Validaciones
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	  private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
//	  @Setter
//	  @Getter
	  @Column(unique = true)
	    private String dni;
	  
	  @NotBlank(message = "El nombre no puede estar vacío") // Asegura que el nombre no sea null o esté vacío
	   @Size(min=1, max = 25, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud del nombre
	    private String nombre;
	  
	  @NotBlank(message = "Los apellidos no puede estar vacío") // Asegura que no sea null o esté vacío
	   @Size(min=1, max = 35, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud 
	    private String apellidos;
	    
	    @NotBlank(message = "El telefono no puede estar vacío") // Asegura que no sea null o esté vacío
	    private Integer telefono;
	    
	    @NotBlank(message = "La direccion no puede estar vacío") // Asegura que no sea null o esté vacío
	    private String direccion;
	    
	    @NotBlank(message = "El email no puede estar vacío") // Asegura que no sea null o esté vacío
	    private String email;
	    
	  @NotBlank(message = "La contraseña no puede estar vacío") // Asegura que no sea null o esté vacío
	    private String password; 
	  
	  /**
	     * Rol del usuario
	     */
	    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
	    @Enumerated(EnumType.STRING)
	    @CollectionTable(name = "usuario_rol")
	    @Column(name = "Roles")
	    private Set<Role> roles = new HashSet<>();

	    /**
	     * Devuelve la colección de roles asignados al usuario.
	     *
	     * @return Colección de roles.
	     */
	    @Transactional
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        roles.size();
	        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
	    }
	    
	  	public Long getId() {
	  		return id;
	  	}

		public void setId(Long id) {
			this.id = id;
		}
	    
		public String getDni() {
			return dni;
		}
	    
	    public void setDni(String dni) {
	        this.dni= dni;
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
	    
		public Integer getTelefono() {
			return telefono;
		}
	    
	    public void setTelefono(Integer telefono) {
	        this.telefono = telefono;
	    }
	    
	    public String getDireccion() {
			return direccion;
		}
	    
	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }
	    
		public String getEmail() {
			return email;
		}
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
		public String getPassword() {
			return password;
		}
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public Set<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(Set<Role> roles) {
	        this.roles = roles;
	    }

	
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}

}