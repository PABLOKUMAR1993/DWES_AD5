package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.dto.Cesta;
import com.camisetas.starwars.model.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import com.camisetas.starwars.model.entity.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {


    //  Atributos


    @Autowired
    private ProductoServiceInt productoService;

    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private TarjetaServiceInt tarjetaService;

    @Autowired
    private PedidoServiceInt pedidoService;

    @Autowired
    private PedidosProductoInt pedidosProductoService;


    // Métodos


    /**
     * Método que muestra la página de inicio.
     * Para mostrar la página de inicio se necesita un listado con todos los productos, pero se quiere mostrar en dos
     * montones, para ello se reparan los productos en dos listas, una con los productos pares y otra con los impares.
     */
    @GetMapping("/")
    public String index(Model model, HttpSession session, Authentication aut) {

        // Recupero el usuario si existe
        Usuario usuario = null;
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero la cesta si existe.
        if (usuario != null) recuperarCarrito(session, aut);

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
    public String registro() { return "registro"; }


    /**
     * Método que recupera un pedido en estado "carrito" de la base de datos y lo almacena en la sesión.
     */
    public void recuperarCarrito(HttpSession session, Authentication aut) {

        // Obtengo el usuario.
        Usuario usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero todos los pedidos del ususario
        List<Pedido> pedidos = pedidoService.buscarPedidosPorIdUsuario(usuario.getIdUsuario());

        // Almaceno el pedido que tiene estado "carrito"
        Pedido pedido = null;
        for (Pedido pedidoUsuario: pedidos) {
            if (pedidoUsuario.getEstado().equals("carrito")) {
                pedido = pedidoUsuario;
                break;
            }
        }

        // Si el pedido existe, recupero la cesta de la base de datos.
        if (pedido != null) {

            // Recupero la lista de líneas de pedido
            List<PedidosProducto> lineasPedido = pedidosProductoService.buscarPorPedido(pedido);

            // Creo la cesta
            List<Cesta> cesta = new ArrayList<>();

            // Recorro la lista de líneas de pedido y creo la cesta
            for (PedidosProducto lineaPedido : lineasPedido) {
                Cesta cesta1 = new Cesta();
                cesta1.setProducto(lineaPedido.getProducto());
                cesta1.setCantidad(lineaPedido.getUnidades());
                cesta.add(cesta1);
            }

            // Publico en sesión la cesta
            session.setAttribute("cesta", cesta);

            // Publico en sesión la cantidad de productos
            session.setAttribute("cantidadProductos", cesta.size());

            // Elimino las líneas del pedido de la base de datos
            for (PedidosProducto lineaPedido : lineasPedido) {
                pedidosProductoService.eliminarLinea(lineaPedido);
            }

            // Elimino el pedido de la base de datos
            pedidoService.eliminarPedido(pedido.getIdPedido());

        }

    }


}
