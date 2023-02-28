package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {

    // Método para buscar una tarjeta por su número.
    @Query("SELECT t FROM Tarjeta t WHERE t.numero = ?1")
    Tarjeta buscarPorNumero(String numero);

}
