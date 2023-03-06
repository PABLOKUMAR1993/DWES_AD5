package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    /**
     * Esta es una consulta parametrizada que busca productos en función de tres parámetros opcionales:
     * búsqueda: una cadena de texto que se utiliza para buscar en el nombre de los productos.
     * alfabetico: una cadena que indica si los resultados deben ordenarse alfabéticamente, y si es así,
     *             si debe ser en orden ascendente o descendente.
     * precio: una cadena que indica si los resultados deben ordenarse por precio, y si es así,
     *         si debe ser en orden ascendente o descendente.
     * La consulta utiliza la cláusula CASE WHEN para definir cómo deben ordenarse los resultados en función de los
     * valores de los parámetros alfabético y precio. En resumen, esta consulta busca productos que coincidan con una
     * cadena de búsqueda, y los ordena alfabética o numéricamente según los parámetros especificados.
     */
    @Query(value=" SELECT p FROM Producto p\n" +
            "WHERE (:busqueda IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')))\n" +
            "ORDER BY\n" +
            "  CASE WHEN :alfabetico = 'asc' THEN p.nombre END ASC,\n" +
            "  CASE WHEN :alfabetico = 'desc' THEN p.nombre END DESC,\n" +
            "  CASE WHEN :precio = 'asc' THEN p.precio END ASC,\n" +
            "  CASE WHEN :precio = 'desc' THEN p.precio END DESC\n ")
    List<Producto> filtroParaCatalogo(String alfabetico, String precio, String busqueda);


    /**
     * Esta consulta devuelve una lista de productos a partir de una lista de IDs.
     */
    @Query(value=" SELECT p FROM Producto p WHERE p.idProducto IN (:ids) ")
    List<Producto> buscarProductosPorIds(List<Integer> ids);


}
