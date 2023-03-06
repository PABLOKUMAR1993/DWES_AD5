package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Rol;
import com.camisetas.starwars.model.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RolServiceImpl implements RolServiceInt {


    // Atributos


    @Autowired
    private RolRepository rolRepository;


    // Métodos Implementados


    /**
     * Método que busca un rol por su id
     */
    @Override
    public Rol buscarPorId(int id) { return rolRepository.findById(id).orElse(null); }


}
