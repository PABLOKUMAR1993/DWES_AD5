package com.camisetas.starwars.model.services;
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

}
