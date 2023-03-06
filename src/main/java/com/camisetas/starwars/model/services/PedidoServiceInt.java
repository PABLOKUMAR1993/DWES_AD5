package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Pedido;
import java.util.List;


public interface PedidoServiceInt {


    List<Pedido> buscarPedidosPorIdUsuario(int idUsuario);
    List<Pedido> buscarTodos();


}
