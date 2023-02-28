package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Direccione;
import com.camisetas.starwars.model.repository.DireccioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DireccioneServiceImpl implements DireccioneServiceInt {


    // Atributos


    @Autowired
    private DireccioneRepository direccioneRepository;


    // Métodos Implementados


    @Override
    public Direccione buscarPorId(int id) { return direccioneRepository.findById(id).orElse(null); }


    /**
     * Método que guarda una dirección en la base de datos.
     */
    @Override
    public boolean guardarDireccion(Direccione direccion) {
        try {
            direccioneRepository.save(direccion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean eliminarDireccion(int idDireccion) {
        try {
            direccioneRepository.deleteById(idDireccion);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
