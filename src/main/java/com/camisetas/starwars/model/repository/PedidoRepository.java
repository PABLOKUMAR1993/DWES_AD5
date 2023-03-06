package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    /**
     * Busca todos los pedidos de un usuario
     */
    @Query("SELECT p FROM Pedido p WHERE p.usuario.idUsuario = ?1")
    List<Pedido> buscarPedidosPorIdUsuario(int idUsuario);


}
