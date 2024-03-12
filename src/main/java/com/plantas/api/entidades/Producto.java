package com.plantas.api.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "productos")
public class Producto {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	  @NotBlank(message = "El nombre no puede estar vacío") // Asegura que el nombre no sea null o esté vacío
	   @Size(min=1, max = 25, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud del nombre
	    private String nombre;
	    

	  @NotBlank(message = "La descripcion no puede estar vacío") // Asegura que  no sea null o esté vacío
	   @Size(min=1, max = 50, message = "Un mínimo de 1 y máximo de 50 caracteres") 
	    private String descripcion;
	    
	  @NotBlank(message = "El stock no puede estar vacío") // Asegura que  no sea null o esté vacío
	    private Integer stock;
	    
	  @NotBlank(message = "El precio no puede estar vacío") // Asegura que  no sea null o esté vacío
	    private Integer precio;
	  
//	  @ManyToMany
//	    @JoinTable(
//	        name = "producto_venta", 
//	        joinColumns = @JoinColumn(name = "producto_id"), 
//	        inverseJoinColumns = @JoinColumn(name = "venta_id")
//	    )
//	    private Set<Venta> ventas;
	    
	    
	    public Long getId() {
	        return id;
	    }

	 
	    public void setId(Long id) {
	        this.id = id;
	    }
		public String getNombre() {
			return nombre;
		}
	    
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	    
		public String getDescripcion() {
			return descripcion;
		}
	    
	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }
	    
		public Integer getStock() {
			return stock;
		}
	    
	    public void setStock(Integer stock) {
	        this.stock = stock;
	    }
	    
		public Integer getPrecio() {
			return precio;
		}
	    
	    public void setPrecio(Integer precio) {
	        this.precio = precio;
	    }
	    
//		public Set<Venta> getVentas() {
//			return ventas;
//		}
//
//
//		public void setVentas(Set<Venta> ventas) {
//			this.ventas = ventas;
//		}
//		
//		public boolean agregarVentas(Venta venta) {
//			return ventas.add(venta);
//		}
	    
	
}