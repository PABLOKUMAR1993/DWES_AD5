package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.entity.PedidosProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PedidosProductoRepository extends JpaRepository<PedidosProducto, Integer> {


    /**
     * Esta consulta devuelve una lista de productos a partir de un pedido.
     */
    @Query(value=" SELECT p FROM PedidosProducto p WHERE p.pedido = (:Pedido) ")
    List<PedidosProducto> buscarPorPedido(Pedido Pedido);


}
