package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Direccion;


public interface DireccionServiceInt {


    Direccion buscarPorId(int id);
    boolean guardarDireccion(Direccion direccion);
    boolean eliminarDireccion(int idDireccion);
    boolean actualizarDireccion(Direccion direccion);


}
