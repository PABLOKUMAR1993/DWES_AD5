package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Producto;
import java.util.List;


public interface ProductoServiceInt {


    List<Producto> buscarTodos();
    List<Producto> filtroParaCatalogo(String alfabetico, String precio, String busqueda);
    Producto buscarPorId(int id);
    List<Producto> buscarProductosPorIds(List<Integer> ids);


}
