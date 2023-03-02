package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Tarjeta;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.TarjetaServiceInt;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class TarjetaController {


    // Atributos


    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private TarjetaServiceInt tarjetaService;


    // Métodos


    /**
     * Este método obtiene el usuario que tiene iniciada sesión, extrae de el las tarjetas que tiene y las envía
     * a la vista mediante el modelo.
     */
    @GetMapping("/pago")
    public String pago(Authentication authentication, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Recupero las tarjetas del usuario.
        List<Tarjeta> tarjetas = usuario.getTarjetas();

        // Envío las tarjetas a la vista.
        model.addAttribute("tarjetas", tarjetas);
        return "pago";

    }


    /**
     * Método que recibe una tarjeta a través de un formulario y la añade a la base de datos.
     * Para ello obtiene la tarjeta por Param y seguidamente recupera el usuario mediante Authentication.
     * Ahora se cruzan los datos, a la tarjeta se le añade el usuario a la lista de usuarios y al usuario
     * se le añade la tarjeta a la lista de tarjetas, y se guardan los cambios en la base de datos.
     */
    @PostMapping("/anyadirTarjeta")
    public String anyadirTarjeta(Tarjeta tarjeta, Model model, RedirectAttributes redirect,
                                 Authentication authentication) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Añado el usuario a la lista de la tarjeta
        List<Usuario> usuarios;
        if (tarjeta.getUsuarios() != null) {
            usuarios = tarjeta.getUsuarios();
        } else {
            usuarios = new ArrayList<>();
        }
        usuarios.add(usuario);
        tarjeta.setUsuarios(usuarios);

        // Añado la tarjeta a la lista de tarjetas del usuario
        List<Tarjeta> tarjetas = usuario.getTarjetas();
        tarjetas.add(tarjeta);
        usuario.setTarjetas(tarjetas);

        // Guardo la tarjeta en la base de datos y mando mensaje de confirmación a la vista.
        if (tarjetaService.guardarTarjeta(tarjeta)) {
            if (usuarioService.actualizarUsuario(usuario)) {
                redirect.addFlashAttribute("mensajeOk", "Tarjeta añadida correctamente");
                // Actualizo el listado de tarjetas.
                model.addAttribute("tarjetas", tarjetas);
                return "redirect:/pago";
            }
            redirect.addFlashAttribute("mensajeParcial", "Tarjeta añadida correctamente, pero usuario no");
            return "redirect:/pago";
        } else {
            redirect.addFlashAttribute("error", "No se ha podido añadir la tarjeta");
            return "redirect:/pago";
        }

    }


    /**
     * Este método recibe el id de una tarjeta y la elimina de la base de datos.
     * Para ello, recupera el usuario mediante Authentication y la tarjeta mediante el id.
     * Seguídamente recupera la lista de tarjetas del usuario y la borra de esa lista.
     * Ahora recupera la lista de usuarios de la tarjeta y lo borra de la lista.
     * Finalmente elimina la tarjeta de la bbdd y actualiza el listado de tarjetas para la vista.
     */
    @PostMapping("/eliminarTarjeta/{id}")
    public String eliminarTarjeta(@PathVariable(name="id") int idTarjeta, Authentication authentication,
                                  RedirectAttributes redirect, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (authentication != null) usuario = usuarioService.buscarPorEmail(authentication.getName());

        // Elimino el usuario de la lista de la tarjeta
        Tarjeta tarjeta = tarjetaService.buscarTarjetaPorId(idTarjeta);
        List<Usuario> usuarios = tarjeta.getUsuarios();
        usuarios.remove(usuario);
        tarjeta.setUsuarios(usuarios);

        // Elimino la tarjeta de la lista de tarjetas del usuario
        List<Tarjeta> tarjetas = usuario.getTarjetas();
        tarjetas.remove(tarjeta);
        usuario.setTarjetas(tarjetas);

        // Guardo los cambios en la base de datos
        if (tarjetaService.eliminarTarjeta(idTarjeta)) {
            redirect.addFlashAttribute("mensajeOk", "Tarjeta eliminada correctamente");
            model.addAttribute("tarjetas", tarjetas);
            return "redirect:/pago";
        } else {
            redirect.addFlashAttribute("error", "No se ha podido eliminar la tarjeta");
            model.addAttribute("tarjetas", tarjetas);
            return "redirect:/pago";
        }

    }


    /**
     * Este método es para als fechas.
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
