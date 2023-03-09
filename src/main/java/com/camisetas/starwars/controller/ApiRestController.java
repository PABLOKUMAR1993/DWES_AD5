package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.services.ProductoServiceInt;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiRestController {


    // Atributos


    @Autowired
    private ProductoServiceInt productoService;

    @Autowired
    private UsuarioServiceInt usuarioService;


    // Métodos


    /**
     * Método que devuelve la documentación de la API-REST.
     */
    @GetMapping("")
    public String documentacionApi() {

        return ("""
        AD-5. - API-REST
        DOCUMENTACIÓN PARA EL USO DE LA API-REST.
    			
    	    
        /api/precioProducto/{id}
        Devuelve el precio de un producto de la base de datos de productos que coincida con el idProducto pasado por argumento.
        
        /api/buscarPorFamilia/{familia}
        Devuelve los productos de nuestra base de datos de productos que pertenecen a la familia pasada como argumento.
        
        /api/clientesPorProvincias
        Devuelve la cantidad de clientes de cada provincia tenemos en nuestra base de datos de clientes.
        
        Realizado por Raúl Herrera & Pavlo Dudnyk.
        """);

    }


    /**
     * Método que devuelve un producto de la base de datos que coincida con el id pasado por argumento.
     */
    @GetMapping("/precioProducto/{id}")
    public String verProducto(@PathVariable("id") int idProducto) {

        // Obtengo el producto de la BBDD por su id.
        Producto producto = productoService.buscarProductoPorId(idProducto);

        // Si el producto es null, devuelvo un mensaje de error.
        if (producto == null) {
            return "No existe el producto con id " + idProducto;
        } else {
            return "El precio del producto "+ producto.getNombre()+ " es: "+producto.getPrecio()+ " €";
        }

    }


    /**
     * Método que devuelve un los productos de la base de datos de productos de la familia pasada por argumento.
     */
    @GetMapping("/buscarPorFamilia/{familia}")
    public String productosPorFamilia(@PathVariable("familia") int idFamilia) {

        List<Producto> productos = productoService.buscarPorFamilia(idFamilia);
        return productos.toString();

    }


    /**
     * Método que devuelve la cantidad de clientes de cada provincia que hay en la BBDD de usuarios.
     */
    @GetMapping("/clientesPorProvincias")
    public List<Object[]> clientesPorProvincia() {

        List<Object[]> usuarios = usuarioService.buscarPorProvincias();
        return usuarios;

    }

}