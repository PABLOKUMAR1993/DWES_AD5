package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Direccion;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.DireccionServiceInt;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
public class DireccionController {


    // Atributos


    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private DireccionServiceInt direccionService;


    // Métodos


    /**
     * Este método obtiene el usuario que tiene iniciada sesión, extrae de el las direcciones que tiene y las envía
     * a la vista mediante el modelo.
     */
    @GetMapping("/direcciones")
    public String direcciones(Authentication aut, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero las direcciones del usuario.
        List<Direccion> direcciones = usuario.getDirecciones();

        // Envío las direcciones a la vista.
        model.addAttribute("direcciones", direcciones);
        return "direcciones";

    }


    /**
     * Este método recibe los datos de una dirección y la añade al usuario que tiene iniciada sesión.
     * Importante comentar que en lugar de recibir una direccion estoy recibiendo los datos de la dirección,
     * esto es así porque dentro de la dirección hay datos que son opcionales y me veo en la obligación de
     * hacerlo de este modo para comprobar que datos llegan antes de construir el objeto direccion.
     */
    @PostMapping("/anyadirDireccion")
    public String anyadirDireccion(@RequestParam("localidad") String localidad, @RequestParam("calle") String calle,
                                   @RequestParam("numero") int numero, @RequestParam("codigoPostal") String codigoPostal,
                                   @RequestParam(name = "piso", required = false) Integer piso,
                                   @RequestParam(name = "letra", required = false) String letra,
                                   Authentication aut, Model model, RedirectAttributes flash) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Creo la dirección usando el constructor que corresponda según si ha rellenado los datos opcionales o no.
        Direccion direccion;
        if (piso == null) {
            direccion = new Direccion(calle, codigoPostal, localidad, numero);
        } else {
            direccion = new Direccion(calle, codigoPostal, letra, localidad, numero, piso);
        }

        // Añado la dirección al usuario.
        usuario.addDireccion(direccion);

        // Actualizo el usuario en la BBDD.
        if (usuarioService.actualizarUsuario(usuario)) {
            // Envío un mensaje de éxito.
            flash.addFlashAttribute("mensajeOk", "Dirección añadida correctamente.");
            // Actualizo la lista de direcciones del usuario.
            List<Direccion> direcciones = usuario.getDirecciones();
            model.addAttribute("direcciones", direcciones);
            return "redirect:/direcciones";
        } else {
            // Envío un mensaje de error.
            flash.addFlashAttribute("mensajeError", "Error al añadir la dirección.");
            return "redirect:/direcciones";
        }

    }


    /**
     * Este método recibe el id de una dirección y la elimina del usuario que tiene iniciada sesión.
     */
    @PostMapping("/eliminarDireccion/{id}")
    public String anyadirDireccion(@PathVariable(name="id") int idDireccion, Authentication aut, Model model,
                                   RedirectAttributes flash) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero la dirección.
        Direccion direccion = direccionService.buscarPorId(idDireccion);

        // Elimino la dirección del usuario.
        usuario.removeDireccion(direccion);

        // Actualizo el usuario en la BBDD.
        if (usuarioService.actualizarUsuario(usuario)) {
            //Si es ok, se habrá eliminado la relación, ahora elimino la dirección.
            if (direccionService.eliminarDireccion(direccion.getIdDireccion())) {
                // Envío un mensaje de éxito.
                flash.addFlashAttribute("mensajeOk", "Dirección eliminada correctamente.");
                // Actualizo la lista de direcciones del usuario para la vista.
                List<Direccion> direcciones = usuario.getDirecciones();
                model.addAttribute("direcciones", direcciones);
                return "redirect:/direcciones";
            } else {
                // Envío un mensaje de error.
                flash.addFlashAttribute("mensajeError",
                        "Error al eliminar la dirección. Se ha eliminado la relación, pero no la dirección.");
                return "redirect:/direcciones";
            }
        } else {
            // Envío un mensaje de error.
            flash.addFlashAttribute("mensajeError", "Error al eliminar la dirección.");
            return "redirect:/direcciones";
        }

    }


    /**
     * Este método recibe el id de una dirección y la envía a la vista para que el usuario pueda editarla.
     */
    @GetMapping("/editarDireccion/{id}")
    public String editarDireccion(@PathVariable("id") int idDireccion, Model model, RedirectAttributes flash) {

        // Recupero la dirección.
        Direccion direccion = direccionService.buscarPorId(idDireccion);

        // Envío la dirección a la vista.
        model.addAttribute("direccion", direccion);
        return "editarDireccion";

    }


    /**
     * Este método recibe los datos de una dirección y la actualiza en la BBDD.
     */
    @PostMapping("/editarDireccion/{id}")
    public String editarDireccion(@PathVariable("id") int idDireccion, Direccion direccion, RedirectAttributes flash) {

        // Le asigno el id de la dirección que se va a editar.
        direccion.setIdDireccion(idDireccion);

        // Actualizo la dirección en la BBDD.
        if (direccionService.actualizarDireccion(direccion)) {
            // Envío un mensaje de éxito.
            flash.addFlashAttribute("mensajeOk", "Dirección editada correctamente.");
            return "redirect:/direcciones";
        } else {
            // Envío un mensaje de error.
            flash.addFlashAttribute("mensajeError", "Error al editar la dirección.");
            return "redirect:/direcciones";
        }

    }

}
