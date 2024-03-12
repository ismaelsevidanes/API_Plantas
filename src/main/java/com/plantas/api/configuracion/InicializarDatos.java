package com.plantas.api.configuracion;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.plantas.api.entidades.Usuario;
import com.plantas.api.entidades.Producto;
import com.plantas.api.entidades.Role;
import com.plantas.api.entidades.Venta;
import com.plantas.api.repositorios.UsuarioRepository;
import com.plantas.api.repositorios.ProductoRepository;
import com.plantas.api.repositorios.VentaRepository;

@Component
public class InicializarDatos implements CommandLineRunner {

	  @Autowired
	    private UsuarioRepository usuarioRepository;
	  
	  @Autowired
	    private ProductoRepository productoRepository;
	  
	  @Autowired
	    private VentaRepository ventaRepository;
	  
	  @Autowired
		private PasswordEncoder passwordEncoder;
	
	  
	  @Override
	    public void run(String... args) throws Exception {
	        
    		Usuario usuario1 =null;
    		Usuario usuario2 =null;
	    	try {
		    	// Usuario 1
	            if (usuarioRepository.findByEmail("ismael.sevidanes@example.com").isEmpty()) {
	            usuario1 = new Usuario();
	            usuario1.setDni("12345678A");
	            usuario1.setNombre("Ismael");
	            usuario1.setApellidos("sevidanes del Moral");
	            usuario1.setTelefono(123456789);
	            usuario1.setDireccion("Calle asa  nº 1");
	            usuario1.setEmail("ismael.sevidanes@example.com");
	            usuario1.setPassword(passwordEncoder.encode("root"));
	            usuario1.getRoles().add(Role.ROLE_ADMIN);
	            usuarioRepository.save(usuario1);
	            }else {
		            System.out.println("El usuario ya existe");
	            }
	            
	         // Usuario 2
	            if (usuarioRepository.findByEmail("manuel.rodriguez@example.com").isEmpty()) {
	            usuario2 = new Usuario();
	            usuario2.setDni("87654321Z");
	            usuario2.setNombre("Manuel");
	            usuario2.setApellidos("Rodriguez");
	            usuario2.setTelefono(987654321);
	            usuario2.setDireccion("Calle bas  nº 2");
	            usuario2.setEmail("manuel.rodriguez@example.com");
	            usuario2.setPassword(passwordEncoder.encode("123"));
	            usuario2.getRoles().add(Role.ROLE_USER);
	            usuarioRepository.save(usuario2);
			} else {
				System.out.println("El usuario ya existe");
			}
	    	
	            
	    	// Producto 1
	            Producto producto1 = new Producto();
	            producto1.setNombre("Amapola");
	            producto1.setDescripcion("Amapola de plastico para regalar a esa persona especial");
	            producto1.setStock(320);
	            producto1.setPrecio(10);
	            productoRepository.save(producto1);
	            
	         // Producto 2
	            Producto producto2 = new Producto();
	            producto2.setNombre("Planta decorativa ");
	            producto2.setDescripcion("Planta decorativa para embellecer el mejor lugar de tu hogar");
	            producto2.setStock(1000);
	            producto2.setPrecio(22);
	            productoRepository.save(producto2);
	            
	         // Producto 3
	            Producto producto3 = new Producto();
	            producto3.setNombre("Cactus");
	            producto3.setDescripcion("Cactus para decorar los lugares mas desolados");
	            producto3.setStock(500);
	            producto3.setPrecio(15);
	            productoRepository.save(producto3);
	            
	         // Producto 4
	            Producto producto4 = new Producto();
	            producto4.setNombre("Bonsai");
	            producto4.setDescripcion("Bonsai perfecto para el jardin");
	            producto4.setStock(200);
	            producto4.setPrecio(30);
	            productoRepository.save(producto4);
	            
	          //Venta 1
                Venta venta1 = new Venta();
				venta1.setUsuario(usuario1);
                venta1.setProducto(producto1);
                venta1.setFecha("2021-05-10");
                ventaRepository.save(venta1);
                
              //Venta 2
                Venta venta2 = new Venta();
                venta2.setUsuario(usuario2);
                venta2.setProducto(producto2);
                venta2.setFecha("2024-05-04");
                ventaRepository.save(venta2);
                                
	    	
	    	}catch(Exception e) {
	            System.out.println("Excepción capturada: " + e.getMessage());
	    	}
	    }
}