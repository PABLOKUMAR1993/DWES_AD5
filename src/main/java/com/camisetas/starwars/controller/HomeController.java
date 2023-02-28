package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.services.ProductoServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    // Atributos


    @Autowired
    private ProductoServiceInt pdao;


    // Métodos


    /**
     * Método que muestra la página de inicio.
     * Dentro de este método, tenemos un listado de productos que se divide en dos, por un lado, los productos
     * con id par y por otro lado los productos con id impar.
     * Así mismo, se envían estos dos listados a la vista para que se muestren en la página de inicio,
     * cada uno en una columna sin repetir productos.
     *
     * @param model Modelo de la página.
     * @return Nombre de la vista.
     */
    @GetMapping("/")
    public String index(Model model) {

        // Recupero la lista de productos.
        List<Producto> listado = pdao.buscarTodos();
        List<Producto> listadoImpares = new ArrayList<>();
        List<Producto> listadoPares = new ArrayList<>();

        // Separo la lista de productos en 2 montones.
        for (Producto producto : listado) {
            if (producto.getIdProducto() % 2 == 0) {
                listadoPares.add(producto);
            } else {
                listadoImpares.add(producto);
            }
        }

        // Envío los listados a la vista.
        model.addAttribute("listadoImpares", listadoImpares);
        model.addAttribute("listadoPares", listadoPares);
        return "index";

    }

}
