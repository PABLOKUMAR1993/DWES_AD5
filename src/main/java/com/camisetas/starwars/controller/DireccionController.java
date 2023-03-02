package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Direccione;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.DireccioneServiceInt;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DireccionController {


    // Atributos


    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private DireccioneServiceInt direccionService;


    // Métodos


    /**
     * Este método obtiene el usuario que tiene iniciada sesión, extrae de el las direcciones que tiene y las envía
     * a la vista mediante el modelo.
     */
    @GetMapping("/direcciones")
    public String direcciones(Authentication authentication, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Recupero las direcciones del usuario.
        List<Direccione> direcciones = usuario.getDirecciones();

        // Envío las direcciones a la vista.
        model.addAttribute("direcciones", direcciones);
        return "direcciones";

    }


    /**
     * Método que recibe una dirección a través de un formulario y la añade a la base de datos.
     * Para ello obtiene la dirección por RequestParam y seguidamente recupera el usuario mediante Authentication.
     * Ahora se cruzan los datos, a la dirección se le añade el usuario a la lista de usuarios y al usuario
     * se le añade la dirección a la lista de direcciones, y se guardan los cambios en la base de datos.
     */
    @PostMapping("/anyadirDireccion")
    public String anyadirDireccion(Direccione direccion, Model model,
                                   Authentication authentication, RedirectAttributes redirect) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Añado el usuario a la lista de usuarios de la dirección.
        List<Usuario> usuarios;
        if (direccion.getUsuarios() != null) {
            usuarios = direccion.getUsuarios();
        } else {
            usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
        direccion.setUsuarios(usuarios);

        // Añado la dirección a la lista de direcciones del usuario.
        List<Direccione> direcciones = usuario.getDirecciones();
        direcciones.add(direccion);
        usuario.setDirecciones(direcciones);

        // Guardo el usuario y devuelvo un mensaje a la vista.
        if (direccionService.guardarDireccion(direccion)) {
            if (usuarioService.actualizarUsuario(usuario)) {
                redirect.addFlashAttribute("mensajeOk", "Dirección añadida correctamente");
                // Actualizo el listado de direcciones.
                model.addAttribute("direcciones", direcciones);
                return "redirect:/direcciones";
            }
            redirect.addFlashAttribute("mensajeParcial",
                    "Dirección añadida correctamente, pero no se ha creado la relación con el usuario");
            return "redirect:/direcciones";
        } else {
            redirect.addFlashAttribute("mensajeError", "No se ha podido añadir la dirección");
            return "redirect:/direcciones";
        }

    }


    /**
     * Este método recibe el id de una dirección y la elimina de la base de datos.
     * Para ello, recupera el usuario mediante Authentication y la dirección mediante el id.
     * Seguidamente recupera la lista de direcciones del usuario y borra la dirección de la lista.
     * Ahora recupera la lista de usuarios de la dirección y lo borra de la lista.
     * Finalmente elimina la dirección de la bbdd y actualiza el listado de direcciones para la vista.
     */
    @PostMapping("/eliminarDireccion/{id}")
    public String eliminarDireccion(@PathVariable(name="id") int idDireccion, Authentication authentication,
                                    RedirectAttributes redirect, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Elimino el usuario de la lista de usuarios de la dirección.
        Direccione direccion = direccionService.buscarPorId(idDireccion);
        List<Usuario> usuarios = direccion.getUsuarios();
        usuarios.remove(usuario);
        direccion.setUsuarios(usuarios);

        // Elimino la dirección de la lista de direcciones del usuario.
        List<Direccione> direcciones = usuario.getDirecciones();
        direcciones.remove(direccion);
        usuario.setDirecciones(direcciones);

        // Guardo los cambios en la base de datos y envío un mensaje a la vista.
        if (direccionService.eliminarDireccion(idDireccion)) {
            redirect.addFlashAttribute("mensajeOk", "Dirección eliminada correctamente");
            model.addAttribute("direcciones", direcciones);
            return "redirect:/direcciones";
        } else {
            redirect.addFlashAttribute("error", "No se ha podido eliminar la dirección");
            model.addAttribute("direcciones", direcciones);
            return "redirect:/direcciones";
        }

    }

}
