package com.camisetas.starwars.model.services;

import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;

import java.util.List;


public interface PedidosProductoInt {


    List<PedidosProducto> buscarTodos();
    boolean guardarLinea(PedidosProducto pedidosProducto);
    List<PedidosProducto> buscarPorPedido(Pedido Pedido);
    boolean eliminarLinea(PedidosProducto linea);


}
