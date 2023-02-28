package com.camisetas.starwars.controller;

import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.services.ProductoServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CatalogoController {

    @Autowired
    private ProductoServiceInt productoSerice;

    /**
     * Método que muestra la página de Catálogo.
     * En esta página se mostrará por defecto un listado de todos los productos que hay en la base de datos.
     * Incluye 2 filtros, uno para ordenar por nombre y otro para ordenar por precio.
     * Adicionalmente cuenta con un buscador que permite buscar por nombre.
     */
    @GetMapping("/catalogo")
    public String catalogo(Model model) {

        List<Producto> listado = productoSerice.buscarTodos();
        List<Producto> listadoImpares = new ArrayList<>();
        List<Producto> listadoPares = new ArrayList<>();

        for (Producto producto : listado) {
            if (producto.getIdProducto() % 2 == 0) {
                listadoPares.add(producto);
            } else {
                listadoImpares.add(producto);
            }
        }

        model.addAttribute("listadoImpares", listadoImpares);
        model.addAttribute("listadoPares", listadoPares);

        return "catalogo";

    }

    /**
     * Método que muestra la página de Catálogo.
     * En esta página se mostrará por defecto un listado de todos los productos que hay en la base de datos.
     * Incluye 2 filtros, uno para ordenar por nombre y otro para ordenar por precio.
     * Adicionalmente cuenta con un buscador que permite buscar por nombre.
     */
    @GetMapping("/catalogo/filtros")
    public String catalogoFiltro(Model model,
                                 @RequestParam("alfabetico") String alfabetico,
                                 @RequestParam("precio") String precio,
                                 @RequestParam("busqueda") String busqueda) {

        List<Producto> listado = productoSerice.filtroParaCatalogo(alfabetico, precio, busqueda);
        List<Producto> listadoImpares = new ArrayList<>();
        List<Producto> listadoPares = new ArrayList<>();

        int i = 1;
        for (Producto producto : listado) {
            if (i % 2 == 0) {
                listadoPares.add(producto);
            } else {
                listadoImpares.add(producto);
            }
            i++;
        }

        model.addAttribute("listadoImpares", listadoImpares);
        model.addAttribute("listadoPares", listadoPares);

        return "catalogo";

    }

    @GetMapping("/catalogo/producto/{id}")
    public String catalogoDetalles(Model model, @PathVariable("id") int id) {

        Producto producto = productoSerice.buscarPorId(id);
        model.addAttribute("producto", producto);
        return "producto";

    }

}
