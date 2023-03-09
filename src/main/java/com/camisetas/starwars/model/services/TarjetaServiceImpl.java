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


    /**
     * Método que busca una tarjeta por su id.
     */
    @Override
    public Tarjeta buscarTarjetaPorId(int idTarjeta) {
        return tarjetaRepository.findById(idTarjeta).orElse(null);
    }


    /**
     * Método que guarda una tarjeta en la base de datos.
     */
    @Override
    public boolean guardarTarjeta(Tarjeta tarjeta) {

        try {
            tarjetaRepository.save(tarjeta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Método que elimina una tarjeta de la base de datos.
     */
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


    /**
     * Método que actualiza una tarjeta de la base de datos.
     */
    @Override
    public boolean actualizarTarjeta(Tarjeta tarjeta) {

        try {
            tarjetaRepository.save(tarjeta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
