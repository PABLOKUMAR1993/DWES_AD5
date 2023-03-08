package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Producto;
import com.camisetas.starwars.model.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductoServiceImpl implements ProductoServiceInt {


    //Atributos


    @Autowired
    ProductoRepository productoRepository;


    // Métodos Implementados


    /**
     * Método que devuelve todos los productos de la base de datos
     */
    @Override
    public List<Producto> buscarTodo() { return productoRepository.findAll(); }


    /**
     * Método que devuelve todos los productos de la base de datos que coincidan con alguno o todos los parámetros
     * pasados por argumentos y los ordena según los parámetros.
     */
    @Override
    public List<Producto> filtroParaCatalogo(String alfabetico, String precio, String busqueda) {
        try {
            return this.productoRepository.filtroParaCatalogo(alfabetico, precio, busqueda);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Método que devuelve un producto de la base de datos que coincida con el id pasado por argumento.
     */
    @Override
    public Producto buscarPorId(int id) { return productoRepository.findById(id).orElse(null); }


    /**
     * Método que devuelve todos los productos de la base de datos que coincidan con alguno o todos los ids
     * pasados por argumentos.
     */
    @Override
    public List<Producto> buscarProductosPorIds(List<Integer> ids) {
        try {
            return this.productoRepository.buscarProductosPorIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Método que devuelve un producto de la base de datos que coincida con el id pasado por argumento.
     */
    @Override
    public Producto buscarProductoPorId(int idProducto) {

        try {
            return this.productoRepository.findById(idProducto).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
