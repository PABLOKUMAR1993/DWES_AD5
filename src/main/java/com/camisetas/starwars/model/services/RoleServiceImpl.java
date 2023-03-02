package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Role;
import com.camisetas.starwars.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleServiceInt{


    // Atributos


    @Autowired
    private RoleRepository roleRepository;


    // Métodos Implementados


    /**
     * Método que busca un rol por su id
     */
    @Override
    public Role buscarPorId(int id) { return roleRepository.findById(id).orElse(null); }


}
