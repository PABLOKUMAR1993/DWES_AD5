package com.camisetas.starwars.model.repository;
import com.camisetas.starwars.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {}
