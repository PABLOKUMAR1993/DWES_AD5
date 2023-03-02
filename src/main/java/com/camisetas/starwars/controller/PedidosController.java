package com.camisetas.starwars.controller;
import com.camisetas.starwars.clasesPropias.PedidoProductoInfo;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PedidosController {


    // Atributos


    @Autowired
    private UsuarioServiceInt usuarioService;


    // Métodos


    /**
     * Método que muestra los pedidos del usuario.
     * Para ello obtengo el usuario y de el la lista de pedidos.
     * Con esa lista itero cada pedido y de cada pedido obtengo la lista de productos.
     * Ahora en un objeto nuevo que he creado llamado PedidoProductoInfo guardo la información que necesito en una
     * sóla lista, para no estar trabajando con 3 listas para obtener todos los datos, cómo la cantidad o el precio
     * que están en listas diferentes.
     * Finalmente creo una lista con todos los productos que ha comprado el usuario y al tener el id del pedido cada
     * producto, puedo saber a qué pedido pertenece cada producto para ordenarlo por producto en la vista.
     */
    @GetMapping("/pedidos")
    public String pedidos(Authentication authentication, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Creo una lista para almacenar la información de pedidos y productos.
        List<PedidoProductoInfo> infoList = new ArrayList<>();

        // Obtengo una lista con los pedidos del usuario.
        List<Pedido> pedidos = usuario.getPedidos();

        // Itero la lista de pedidos del usuario
        for (Pedido pedido : usuario.getPedidos()) {

            //Itero la lista de productos de cada pedido
            for (PedidosProducto pedidoProducto : pedido.getPedidosProductos()) {

                // Obtengo el objeto producto asociado a cada pedido.
                Producto producto = pedidoProducto.getProducto();

                // Creo un objeto PedidoProductoInfo.
                // Lo lleno con setters porque me parece que el código se ve más elegante y
                // se lee mejor que con un constructor.
                PedidoProductoInfo pedidoProductoInfo = new PedidoProductoInfo();
                pedidoProductoInfo.setIdPedido(pedido.getIdPedido());
                pedidoProductoInfo.setEstadoPedido(pedido.getEstado());
                pedidoProductoInfo.setFechaPedido(pedido.getFecha());
                pedidoProductoInfo.setImagen(producto.getImagen());
                pedidoProductoInfo.setNombreProducto(producto.getNombre());
                pedidoProductoInfo.setPrecioUnitario(pedidoProducto.getPrecioUnitario());
                pedidoProductoInfo.setCantidad(pedidoProducto.getUnidades());

                // Añado el objeto PedidoProductoInfo a la lista
                infoList.add(pedidoProductoInfo);
            }

        }

        // Añado la lista de pedidos y la lista de información de pedidos y productos a la vista.
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("infoList", infoList);
        return "pedidos";

    }


}
