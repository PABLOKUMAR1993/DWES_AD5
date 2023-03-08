package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.services.ProductoServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {


    // Atributos


    @Autowired
    private ProductoServiceInt productoService;


    // Métodos


    /**
     * Método que devuelve un producto de la base de datos que coincida con el id pasado por argumento.
     */
    @GetMapping("/{id}")
    public String verProducto(@PathVariable("id") int idProducto) {

        // Obtengo el producto de la BBDD por su id.
        Producto producto = productoService.buscarProductoPorId(idProducto);

        // Si el producto es null, devuelvo un mensaje de error.
        if (producto == null) {
            return "No existe el producto con id " + idProducto;
        } else {
            return producto.toString();
        }

    }


}
