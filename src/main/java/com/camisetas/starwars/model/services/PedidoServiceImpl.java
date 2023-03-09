package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Pedido;
import com.camisetas.starwars.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PedidoServiceImpl implements PedidoServiceInt {


    // Atributos


    @Autowired
    private PedidoRepository pedidoRepository;


    // Métodos


    /**
     * Método que busca pedidos por el ID del usuario.
     */
    @Override
    public List<Pedido> buscarPedidosPorIdUsuario(int idUsuario) {

        try {
            return pedidoRepository.buscarPedidosPorIdUsuario(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Método que busca todos los pedidos que hay en la base de datos.
     */
    @Override
    public List<Pedido> buscarTodos() {

        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * Método que guarda un pedido en la base de datos.
     */
    @Override
    public boolean guardarPedido(Pedido pedido) {

        try {
            pedidoRepository.save(pedido);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Método que elimina un pedido de la base de datos.
     */
    @Override
    public boolean eliminarPedido(int idPedido) {

        try {
            pedidoRepository.deleteById(idPedido);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
