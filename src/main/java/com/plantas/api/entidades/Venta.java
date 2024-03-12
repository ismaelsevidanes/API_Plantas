package com.plantas.api.entidades;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Ventas")
public class Venta {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  
	  // FK de la tabla cliente del id
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "usuario_id")
	    @NotNull(message = "El usuario es obligatorio") // Asegura que el usuario tenga una venta asociada
	    private Usuario usuario;
	  
	  private String fecha;
	  
	  
	    
//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "ventas")
//	    private Set<Producto> productos = new HashSet<>();
	  
	  
	  @ManyToOne
	    @JoinColumn(name = "producto_id")
	    private Producto producto;

	  
	  public Long getId() {
	        return id;
	    }

	 
	    public void setId(Long id) {
	        this.id = id;
	    }
	  public Producto getClase() {
	        return producto;
	    }

	    /**
	     * Establece la clase a la que pertenece el estudiante.
	     *
	     * @param clase Clase a la que pertenece el estudiante.
	     */
	    public void setClase(Producto producto) {
	        this.producto = producto;
	    }
	  
	  
		public String getFecha() {
			return fecha;
		}
	    
	    public void setFecha(String fecha) {
	        this.fecha = fecha;
	    }
	    
		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		public Producto getProducto() {
			return producto;
		}
		
		public void setProducto(Producto producto) {
			this.producto = producto;
		}
	    
	
}