package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.dto.PedidoProductoInfo;
import com.camisetas.starwars.model.dto.TotalPedido;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.PedidoServiceInt;
import com.camisetas.starwars.model.services.PedidosProductoInt;
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
    private PedidoServiceInt pedidoService;


    // Métodos


    /**
     * Método para obtener y mostrar todos los pedidos de un usuario.
     * Para ello se han creado dos DTO, uno para almacenar conjuntamente la información de un pedido y sus productos
     * y el otro para almacenar el id del pedido con el importe total de los productos y cantidades.
     */
    @GetMapping("/pedidos")
    public String mostrarPedidos(Authentication aut, Model model) {

        // Obtengo el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Obtengo todos los pedidos del usuario.
        List<Pedido> pedidos = pedidoService.buscarPedidosPorIdUsuario(usuario.getIdUsuario());

        // Creo una lista para almacenar la información de pedidos y productos.
        List<PedidoProductoInfo> infoList = new ArrayList<>();

        // Itero la lista de pedidos del usuario
        for (Pedido pedido : pedidos) {

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
                pedidoProductoInfo.setPrecioUnitario(pedidoProducto.getPrecio());
                pedidoProductoInfo.setCantidad(pedidoProducto.getUnidades());

                // Añado el objeto a la lista.
                infoList.add(pedidoProductoInfo);

            }
        }

        // Hago una lista con totales de pedidos según el id de los productos.
        List<TotalPedido> totales = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            double total = 0.0;
            for (int j = 0; j < infoList.size(); j++) {
                if ( pedidos.get(i).getIdPedido() == infoList.get(j).getIdPedido() ) {
                    total += infoList.get(j).getPrecioUnitario() * infoList.get(j).getCantidad();
                    totales.add(new TotalPedido(pedidos.get(i).getIdPedido(), total));
                }
            }
        }

        // Ahora en una nueva lista, suprimo los duplicados y sumo los totales que coincidan por el ID.
        List<TotalPedido> totales2 = new ArrayList<>();
        for (int i = 0; i < totales.size(); i++) {
            boolean existe = false;
            for (int j = 0; j < totales2.size(); j++) {
                if (totales.get(i).getIdPedido() == totales2.get(j).getIdPedido()) {
                    totales2.get(j).setTotalPedido(totales2.get(j).getTotalPedido() + totales.get(i).getTotalPedido());
                    existe = true;
                }
            }
            if (!existe) totales2.add(totales.get(i));
        }

        // Añado la lista de pedidos y la lista de información de pedidos y productos a la vista.
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("infoList", infoList);
        model.addAttribute("totales", totales2);
        return "pedidos";

    }


}