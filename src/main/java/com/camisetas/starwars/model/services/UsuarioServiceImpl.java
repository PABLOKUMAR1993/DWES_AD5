package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioServiceInt {


    // Atributos


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolServiceInt roleService;


    // Métodos Implementados


    /**
     * Método que crea un usuario.
     * Recibe el usuario por parámetro y comprueba si existe mediante el email, ya que el id es 0 hasta que el
     * usuario es creado en la base de datos. Si no hay otro email igual y todos los datos son correctos,
     * lo guarda en la base de datos.
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
     * Mediante la clase Calendar, comprueba si el usuario es mayor de edad.
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

        // Si la fecha de nacimiento es posterior al mes actual, o si es igual al mes actual,
        // pero el día de nacimiento es posterior al día actual, se resta un año.
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


    /**
     * Método el cual sobreescribe un usuario en la base de datos.
     */
    @Override
    public boolean actualizarUsuario(Usuario usuario) {

        try {
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Método que devuelve una lista con la cantidad de usuarios que hay en cada provincia
     */
    @Override
    public List<Object[]> buscarPorProvincias() {

        try {
            return usuarioRepository.buscarPorProvincias();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
