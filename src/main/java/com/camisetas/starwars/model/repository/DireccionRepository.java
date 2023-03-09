package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    /**
     * Nos tiene que devolver una lista de con cuantos usu<rios hay de cada provincia
     */
    @Query("SELECT COUNT(u.idUsuario), dir.provincia\n" +
            "FROM Usuario u \n" +
            "JOIN u.direcciones dir\n" +
            "GROUP BY dir.provincia\n")
    List<Object[]> buscarPorProvincias();

}
