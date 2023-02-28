package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    //Método con query para que me devuelva un usuario por su email.
    @Query("select u from Usuario u where u.email = ?1")
    Usuario buscarPorEmail(String email);

}