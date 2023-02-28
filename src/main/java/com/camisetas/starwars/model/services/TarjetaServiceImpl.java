package com.camisetas.starwars.model.services;

import com.camisetas.starwars.model.entity.Tarjeta;
import com.camisetas.starwars.model.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl implements TarjetaServiceInt {


    // Atributos


    @Autowired
    private TarjetaRepository tarjetaRepository;


    // Métodos Implementados


    @Override
    public Tarjeta buscarTarjetaPorId(int idTarjeta) {
        return tarjetaRepository.findById(idTarjeta).orElse(null);
    }

    /**
     * Método que guarda una tarjeta en la base de datos.
     * Comprueba si existe otra igual, si no existe, la guarda.
     */
    @Override
    public boolean guardarTarjeta(Tarjeta tarjeta) {

        if (tarjetaRepository.buscarPorNumero(tarjeta.getNumero()) != null) {
            System.out.println("Ya existe una tarjeta con ese número");
            return false;
        } else {
            try {
                System.out.println("tarjeta guardada");
                tarjetaRepository.save(tarjeta);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    @Override
    public boolean eliminarTarjeta(int idTarjeta) {
        try {
            tarjetaRepository.deleteById(idTarjeta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
