package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Direccione;


public interface DireccioneServiceInt {


    Direccione buscarPorId(int id);
    boolean guardarDireccion(Direccione direccion);
    boolean eliminarDireccion(int idDireccion);


}
