package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.dto.Cesta;
import com.camisetas.starwars.model.entity.*;
import com.camisetas.starwars.model.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class CarritoController {


    // Atributos


    @Autowired
    private ProductoServiceInt productoService;

    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private PedidoServiceInt pedidoService;

    @Autowired
    private PedidosProductoInt pedidosProductoService;


    // Métodos


    /**
     * Método para añadir un producto a la cesta de la compra.
     */
    @GetMapping("/anyadirCarrito/{idProducto}/{pagina}")
    public String anyadirCarrito(@PathVariable("idProducto") int idProducto, @PathVariable("pagina") String pagina,
                                 HttpSession session, Model model, Authentication aut) {

        // Si el usuario no está autenticado, devuelvo a la página de la que proviene y le muestro un mensaje.
        if (aut == null) {

            model.addAttribute("mensajeCarritoError",
                    "Para poder comprar productos, tienes que iniciar sesión.");

            switch (pagina) {
                case "index":
                    return "forward:/";
                case "catalogo":
                    return "forward:/catalogo";
                case "producto":
                    return "forward:/catalogo/producto/" + idProducto;
                default:
                    return "forward:/";
            }

        } else { // Si el usuario está autenticado, añado el producto a la cesta.

            // Recupero la cesta de sesión, por si existe, si no, creo una nueva.
            List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");
            if (cesta == null) cesta = new ArrayList<>();

            // Creo un objeto de tipo Cesta
            Cesta cesta1 = new Cesta();

            // Creo una variable de tipo Producto y le asigno el producto que me llega por parámetro
            Producto producto = productoService.buscarPorId(idProducto);

            // Le asigno los valores que me llegan por parámetro
            cesta1.setProducto(producto);
            cesta1.setCantidad(1);

            // Antes de añadir el producto compruebo si está y devuelvo mensaje.
            if (cesta.contains(cesta1)) {
                // Mensaje de error
                model.addAttribute("mensajeCarritoError",
                        "No se ha añadido a la cesta, porque este producto ya está en ella.");
            } else {
                // Añado el producto a la lista
                cesta.add(cesta1);
                // Publico la lista en sesión
                session.setAttribute("cesta", cesta);
                // Mensaje de confirmación
                model.addAttribute("mensajeCarritoOk", "Producto añadido correctamente.");
            }

            // publico en sesion un número que será la cantidad de productos que hay en la cesta
            session.setAttribute("cantidadProductos", cesta.size());

            // Redirijo a la página que me han pasado por parámetro
            switch (pagina) {
                case "index":
                    return "forward:/";
                case "catalogo":
                    return "forward:/catalogo";
                case "producto":
                    return "forward:/catalogo/producto/" + idProducto;
                default:
                    return "forward:/";
            }

        }

    }


    /**
     * Método para ver el contenido de la cesta de la compra.
     */
    @GetMapping("/verCarrito")
    public String verCarrito(HttpSession session, Model model) {

        // Recupero la cesta de sesión, por si existe, si no, creo una nueva.
        List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");
        if (cesta == null)  cesta = new ArrayList<>();

        // Calculo el subtotal de la cesta multiplicando la cantidad de productos por su precio
        double subtotal = 0;
        for (Cesta cesta1 : cesta) {
            subtotal += cesta1.getCantidad() * cesta1.getProducto().getPrecio();
        }

        // Envío la lista y el subtotal a la vista
        model.addAttribute("productos", cesta);
        model.addAttribute("subtotal", subtotal);

        return "carrito";

    }


    /**
     * Método para eliminar un producto de la cesta de la compra.
     */
    @GetMapping("/eliminarProducto/{idProducto}")
    public String eliminarProducto(@PathVariable("idProducto") int idProducto, HttpSession session,
                                   RedirectAttributes flash) {

        // Recupero la cesta de sesión.
        List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");

        // Creo un objeto de tipo Producto con el id que me han pasado
        Producto producto = productoService.buscarPorId(idProducto);

        // Elimino la línea de cesta que contiene el producto que me han pasado
        cesta.removeIf(cesta1 -> cesta1.getProducto().equals(producto));

        // Publico en sesión la nueva cesta
        session.setAttribute("cesta", cesta);

        // Actualizo el número de productos en sesión
        session.setAttribute("cantidadProductos", cesta.size());

        // Mensaje de confirmación
        flash.addFlashAttribute("mensajeCarritoEliminarOk",
                "Producto eliminado correctamente.");

        return "redirect:/verCarrito";

    }


    /**
     * Método para actualizar la cantidad de un producto de la cesta de la compra.
     */
    @GetMapping("/actualizarCantidad/{id}")
    public String actualizarCantidad(@RequestParam("cantidad") int cantidad, @PathVariable("id") int idProducto,
                                     HttpSession session, RedirectAttributes flash) {

        // Recupero la cesta de sesión.
        List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");

        // Creo un objeto de tipo Producto con el id que me han pasado
        Producto producto = productoService.buscarPorId(idProducto);

        // Recorro la cesta y actualizo la cantidad del producto que me han pasado
        for (Cesta cesta1 : cesta) {
            if (cesta1.getProducto().equals(producto)) {
                cesta1.setCantidad(cantidad);
            }
        }

        // Publico en sesión la nueva cesta
        session.setAttribute("cesta", cesta);

        // Mensaje de confirmación
        flash.addFlashAttribute("mensajeCarritoActualizarOk",
                "Cantidad actualizada correctamente.");

        return "redirect:/verCarrito";

    }


    /**
     * Método para vaciar la cesta de la compra.
     */
    @GetMapping("/vaciarCesta")
    public String vaciarCesta(HttpSession session, RedirectAttributes flash) {

        // Elimino la cesta de sesión
        session.removeAttribute("cesta");

        // Elimino el número de productos de sesión
        session.removeAttribute("cantidadProductos");

        // Mensaje de confirmación
        flash.addFlashAttribute("mensajeCarritoActualizarOk",
                "Cesta vaciada correctamente.");

        return "redirect:/verCarrito";

    }


    /**
     * Este método recibirá la lista de productos de la cesta de la compra, la dirección de envío y la tarjeta de pago.
     * Se encargará de crear el pedido y de devolver a la vista con un mensaje de que el pedido está correcto.
     */
    @GetMapping("/pedidoCompletado")
    public String pedidoCompletado(HttpSession session, Authentication aut, Model model) {

        // Recupero la cesta de sesión.
        List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");

        // Recupero la dirección de envío elegida por el cliente
        Direccion direccion = (Direccion) session.getAttribute("direccionEnvioPedido");

        // Recupero la tarjeta de pago elegida por el cliente
        Tarjeta tarjeta = (Tarjeta) session.getAttribute("tarjetaEnvioPedido");

        // Obtengo el usuario.
        Usuario usuario = usuarioService.buscarPorEmail(aut.getName());

        // Creo el pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccion(direccion);
        pedido.setTarjeta(tarjeta);
        pedido.setFecha(new Date());
        pedido.setEstado("completado");

        // Guardo el pedido
        pedidoService.guardarPedido(pedido);

        // Recorro la cesta y creo las líneas de pedido
        for (Cesta cesta1 : cesta) {
            PedidosProducto lineaPedido = new PedidosProducto();
            lineaPedido.setPedido(pedido);
            lineaPedido.setProducto(cesta1.getProducto());
            lineaPedido.setUnidades(cesta1.getCantidad());
            lineaPedido.setPrecio(cesta1.getProducto().getPrecio());
            pedidosProductoService.guardarLinea(lineaPedido);
        }

        // Elimino la cesta de sesión
        session.removeAttribute("cesta");

        // Elimino el número de productos de sesión
        session.removeAttribute("cantidadProductos");

        // Elimino la dirección de envío de sesión
        session.removeAttribute("direccionEnvioPedido");

        // Elimino la tarjeta de pago de sesión
        session.removeAttribute("tarjetaEnvioPedido");

        // Publico el pedido y un mensaje para la vista
        model.addAttribute("pedido", pedido);
        model.addAttribute("mensajePedidoOk", "Pedido completado correctamente.");

        return "pedidoCompletado";

    }


    /**
     * Método que antes de cerrar sesión, guarda el carrito en la base de datos.
     */
    @GetMapping("/guardarCesta")
    public String logout(HttpSession session, Authentication aut) {

        // Recupero la cesta de sesión.
        List<Cesta> cesta = (List<Cesta>) session.getAttribute("cesta");

        if (cesta != null) {
            // Recupero la dirección de envío elegida por el cliente
            Direccion direccion = (Direccion) session.getAttribute("direccionEnvioPedido");

            // Recupero la tarjeta de pago elegida por el cliente
            Tarjeta tarjeta = (Tarjeta) session.getAttribute("tarjetaEnvioPedido");

            // Obtengo el usuario.
            Usuario usuario = usuarioService.buscarPorEmail(aut.getName());

            // Creo el pedido
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setDireccion(direccion);
            pedido.setTarjeta(tarjeta);
            pedido.setFecha(new Date());
            pedido.setEstado("carrito");

            // Guardo el pedido
            pedidoService.guardarPedido(pedido);

            // Recorro la cesta y creo las líneas de pedido
            for (Cesta cesta1 : cesta) {
                PedidosProducto lineaPedido = new PedidosProducto();
                lineaPedido.setPedido(pedido);
                lineaPedido.setProducto(cesta1.getProducto());
                lineaPedido.setUnidades(cesta1.getCantidad());
                lineaPedido.setPrecio(cesta1.getProducto().getPrecio());
                pedidosProductoService.guardarLinea(lineaPedido);
            }

            // Elimino la cesta de sesión
            session.removeAttribute("cesta");

            // Elimino el número de productos de sesión
            session.removeAttribute("cantidadProductos");

            // Elimino la dirección de envío de sesión
            session.removeAttribute("direccionEnvioPedido");

            // Elimino la tarjeta de pago de sesión
            session.removeAttribute("tarjetaEnvioPedido");

        }

        return "redirect:/logout";

    }


}