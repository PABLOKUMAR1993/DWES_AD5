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
    ProductoRepository prepo;

    // Métodos Implementados

    @Override
    public List<Producto> buscarTodos() { return prepo.findAll(); }

    @Override
    public List<Producto> filtroParaCatalogo(String alfabetico, String precio, String busqueda) {
        try {
            return this.prepo.filtroParaCatalogo(alfabetico, precio, busqueda);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Producto buscarPorId(int id) { return prepo.findById(id).orElse(null); }

}
