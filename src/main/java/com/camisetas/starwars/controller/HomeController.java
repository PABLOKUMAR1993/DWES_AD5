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


    //  Atributos


    @Autowired
    private ProductoServiceInt productoService;


    // Métodos


    /**
     * Método que muestra la página de inicio.
     * Para mostrar la página de inicio se necesita un listado con todos los productos, pero se quiere mostrar en dos
     * montones, para ello se reparan los productos en dos listas, una con los productos pares y otra con los impares.
     */
    @GetMapping("/")
    public String index(Model model){

        // Recupero la lista de productos.
        List<Producto> listado = productoService.buscarTodo();

        // Creo las listas de productos pares e impares.
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


    /**
     * Método que devuelve la vista login.jsp
     */
    @GetMapping("/login")
    public String login() { return "login"; }


    /**
     * Método que devuelve la vista registro.jsp
     */
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }



}
