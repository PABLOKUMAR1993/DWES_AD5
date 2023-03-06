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


    // Atributos


    @Autowired
    private ProductoServiceInt productoService;


    // Métodos


    /**
     * Método que muestra la página de Catálogo.
     * En esta página se mostrará por defecto un listado de todos los productos que hay en la base de datos.
     * Al recibir el listado, lo divide en dos, para hacer dos columnas de productos.
     * En una columna los pares y en la otra los impares.
     * Envía estos dos listados a la vista mediante el modelo.
     */
    @GetMapping("/catalogo")
    public String catalogo(Model model) {

        // Listado de todos los productos.
        List<Producto> listado = productoService.buscarTodo();

        // Listados de productos para impares y pares.
        List<Producto> listadoImpares = new ArrayList<>();
        List<Producto> listadoPares = new ArrayList<>();

        // Recorremos el listado de productos y lo repartimos.
        int i = 0;
        for (Producto producto : listado) {
            if (i % 2 == 0) {
                listadoPares.add(producto);
            } else {
                listadoImpares.add(producto);
            }
            i++;
        }

        // Enviamos los listados a la vista.
        model.addAttribute("listadoImpares", listadoImpares);
        model.addAttribute("listadoPares", listadoPares);
        return "catalogo";

    }


    /**
     * Este método recibe los parámetros de la vista de Catálogo.
     * Los parámetros son: alfabético, precio y búsqueda.
     * Basándose en esos parámetros hace una consulta a la base de datos y devuelve un listado de productos filtrado.
     * Igual que en el método anterior, divide el listado en dos y lo envía a la vista.
     *
     * @param alfabetico Parámetro de ordenación alfabética. Puede ser "asc" o "desc".
     * @param precio Parámetro de ordenación por precio. Puede ser "asc" o "desc".
     * @param busqueda Parámetro de búsqueda. Puede ser una cadena de texto o vacío.
     */
    @GetMapping("/catalogo/filtros")
    public String catalogoFiltro(Model model, @RequestParam("alfabetico") String alfabetico,
                                 @RequestParam("precio") String precio, @RequestParam("busqueda") String busqueda) {

        // Listado de productos filtrado.
        List<Producto> listado = productoService.filtroParaCatalogo(alfabetico, precio, busqueda);

        // Listado de productos con ID impar y par.
        List<Producto> listadoImpares = new ArrayList<>();
        List<Producto> listadoPares = new ArrayList<>();

        // Recorremos el listado de productos y lo repartimos.
        int i = 1;
        for (Producto producto : listado) {
            if (i % 2 == 0) {
                listadoPares.add(producto);
            } else {
                listadoImpares.add(producto);
            }
            i++;
        }

        // Enviamos los listados a la vista.
        model.addAttribute("listadoImpares", listadoImpares);
        model.addAttribute("listadoPares", listadoPares);
        return "catalogo";

    }


    /**
     * Este método recibe el ID de un producto y devuelve la vista de detalles de ese producto.
     * A diferencia de los dos métodos anteriores, abre una página nueva, una página individual con los detalles
     * del producto indicado por su ID.
     *
     * @param id ID del producto.
     */
    @GetMapping("/catalogo/producto/{id}")
    public String catalogoDetalles(Model model, @PathVariable("id") int id) {

        // Buscamos el producto por su ID.
        Producto producto = productoService.buscarPorId(id);

        // Enviamos el producto a la vista.
        model.addAttribute("producto", producto);
        return "producto";

    }


}
