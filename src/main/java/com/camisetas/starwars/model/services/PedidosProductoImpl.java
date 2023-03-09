package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;
import com.camisetas.starwars.model.repository.PedidosProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidosProductoImpl implements PedidosProductoInt {


    // Atributos


    @Autowired
    private PedidosProductoRepository productosPedidoRepo;


    // Métodos


    /**
     * Método que devuelve todos los productosPedidos
     */
    @Override
    public List<PedidosProducto> buscarTodos() {
        return productosPedidoRepo.findAll();
    }


    /**
     * Método que devuelve un productoPedido por su id
     */
    @Override
    public boolean guardarLinea(PedidosProducto pedidosProducto) {

        try {
            productosPedidoRepo.save(pedidosProducto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Método que devuelve un productoPedido por su id
     */
    @Override
    public List<PedidosProducto> buscarPorPedido(Pedido Pedido) {

        try {
            return productosPedidoRepo.buscarPorPedido(Pedido);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Método que elimina un productoPedido de la base de datos.
     */
    @Override
    public boolean eliminarLinea(PedidosProducto linea) {
        try {
            productosPedidoRepo.delete(linea);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
