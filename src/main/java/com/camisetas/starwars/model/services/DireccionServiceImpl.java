package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Direccion;
import com.camisetas.starwars.model.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DireccionServiceImpl implements DireccionServiceInt {


    // Atributos


    @Autowired
    private DireccionRepository direccionRepository;


    // Métodos


    /**
     * Método que busca una dirección por su id.
     */
    @Override
    public Direccion buscarPorId(int id) { return direccionRepository.findById(id).orElse(null); }


    /**
     * Método que guarda una dirección en la base de datos.
     */
    @Override
    public boolean guardarDireccion(Direccion direccion) {
        try {
            direccionRepository.save(direccion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Método que elimina una dirección de la base de datos.
     */
    @Override
    public boolean eliminarDireccion(int idDireccion) {
        try {
            direccionRepository.deleteById(idDireccion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Método que actualiza una dirección de la base de datos.
     */
    @Override
    public boolean actualizarDireccion(Direccion direccion) {
        try {
            direccionRepository.save(direccion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
