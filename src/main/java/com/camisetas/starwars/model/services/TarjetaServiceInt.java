package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Tarjeta;


public interface TarjetaServiceInt {


    Tarjeta buscarTarjetaPorId(int idTarjeta);
    boolean guardarTarjeta(Tarjeta tarjeta);
    boolean eliminarTarjeta(int idTarjeta);


}
