package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Integer> {}