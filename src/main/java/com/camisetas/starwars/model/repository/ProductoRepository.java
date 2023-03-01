package com.camisetas.starwars.model.repository;

import com.camisetas.starwars.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    @Query(value=" SELECT p FROM Producto p\n" +
            "WHERE (:busqueda IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')))\n" +
            "ORDER BY\n" +
            "  CASE WHEN :alfabetico = 'asc' THEN p.nombre END ASC,\n" +
            "  CASE WHEN :alfabetico = 'desc' THEN p.nombre END DESC,\n" +
            "  CASE WHEN :precio = 'asc' THEN p.precio END ASC,\n" +
            "  CASE WHEN :precio = 'desc' THEN p.precio END DESC\n ")
    List<Producto> filtroParaCatalogo(String alfabetico, String precio, String busqueda);

    // Método para buscar productos por IDs.
    @Query(value=" SELECT p FROM Producto p WHERE p.idProducto IN (:ids) ")
    List<Producto> buscarProductosPorIds(List<Integer> ids);


}
