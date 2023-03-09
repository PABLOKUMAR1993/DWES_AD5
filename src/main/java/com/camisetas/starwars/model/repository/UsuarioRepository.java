package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{


    /**
     * Método que busca um usuario por su email
     */
    @Query("select u from Usuario u where u.email = ?1")
    Usuario buscarPorEmail(String email);

    /**
     * Consulta que muestra la cantidad de usuarios que hay por provincias.
     */
    @Query("select count(u.idUsuario), d.provincia from Usuario u join u.direcciones d group by d.provincia")
    List<Object[]> buscarPorProvincias();


}