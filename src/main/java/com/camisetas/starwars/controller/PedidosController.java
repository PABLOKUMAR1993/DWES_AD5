package com.camisetas.starwars.controller;
import com.camisetas.starwars.clasesPropias.PedidoProductoInfo;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.ProductoServiceInt;
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

    @Autowired
    private ProductoServiceInt productoService;


    // Métodos


    /**

     */
    @GetMapping("/pedidos")
    public String pedidos(Authentication authentication, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Creo una lista para almacenar la información de pedidos y productos
        List<PedidoProductoInfo> infoList = new ArrayList<>();

        // Obtengo una lista para almacenar los pedidos del usuario
        List<Pedido> pedidos = usuario.getPedidos();

        // Itero la lista de pedidos del usuario
        for (Pedido pedido : usuario.getPedidos()) {

            //Itero la lista de productos del pedido
            for (PedidosProducto pedidoProducto : pedido.getPedidosProductos()) {

                // Obtengo el objeto producto asociado al pedidoProducto
                Producto producto = pedidoProducto.getProducto();

                // Creo un objeto PedidoProductoInfo
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

        System.out.println("info list" + infoList);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("infoList", infoList);

        // Envío los pedidos a la vista.
        return "pedidos";

    }

}
