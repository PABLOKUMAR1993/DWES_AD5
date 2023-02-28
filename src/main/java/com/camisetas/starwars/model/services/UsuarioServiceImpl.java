package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;


@Service
public class UsuarioServiceImpl implements UsuarioServiceInt {


    // Atributos


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleServiceInt roleService;


    // Métodos Implementados


    /**
     * Método que crea un usuario.
     * Recibe el usuario por parámetro y comprueba si existe mediante el email, ya que el id es 0 hasta que el
     * autoincremente de la base de datos le asigna un id definitivo.
     * Si no hay otro email igual y todos los datos son correctos, lo guarda en la base de datos.
     *
     * @param usuario Objeto usuario.
     * @return true si se ha creado correctamente, false y excepción si no.
     */
    @Override
    public boolean crearUsuario(Usuario usuario) {

        if (usuarioRepository.buscarPorEmail(usuario.getEmail()) != null) {
            return false;
        } else {
            try {
                usuarioRepository.save(usuario);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    }


    /**
     * Comprueba si el usuario es mayor de edad
     *
     * @param usuario Objeto usuario enviado al método del cual se extrae la fecha de nacimiento.
     * @return True si es mayor de edad, false si no lo es.
     */
    @Override
    public boolean comprobarEdad(Usuario usuario) {

        // Obtenemos la fecha actual
        Calendar ahora = Calendar.getInstance();
        // Obtenemos la fecha de nacimiento del usuario
        Calendar fechaNacimiento = Calendar.getInstance();
        // Establecemos la fecha de nacimiento del usuario
        fechaNacimiento.setTime(usuario.getFechaNacimiento());
        // Calculamos la diferencia de años entre la fecha actual y la fecha de nacimiento
        int difererenciaEnAnyos = ahora.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        // Si la fecha de nacimiento es posterior al mes actual,
        // o si es igual al mes actual pero el día de nacimiento es posterior al día actual, se resta un año
        if( fechaNacimiento.get(Calendar.MONTH) > ahora.get(Calendar.MONTH) ||
                fechaNacimiento.get(Calendar.MONTH) == ahora.get(Calendar.MONTH) &&
                        fechaNacimiento.get(Calendar.DAY_OF_MONTH) > ahora.get(Calendar.DAY_OF_MONTH)) {
            difererenciaEnAnyos--;
        }

        // Si la diferencia de años es mayor o igual a 18, es mayor de edad
        return difererenciaEnAnyos >= 18;

    }


    /**
     * Método que busca un usuario por su email.
     *
     * @param email string con el Email del usuario a buscar.
     * @return Objeto usuario si lo encuentra, null si no.
     */
    @Override
    public Usuario buscarPorEmail(String email) {

        try {
            return usuarioRepository.buscarPorEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {

        try {
            usuarioRepository.save(usuario);
            System.out.println("Usuario vinculado con la tarjeta correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("No se ha podido vincular el usuario con la tarjeta");
            e.printStackTrace();
            return false;
        }

    }

}
