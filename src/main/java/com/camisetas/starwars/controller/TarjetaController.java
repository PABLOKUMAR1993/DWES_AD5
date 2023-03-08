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
     * Este método obtiene el usuario que tiene iniciada sesión, extrae de él las tarjetas que tiene y las envía
     * a la vista mediante el modelo.
     */
    @GetMapping("/tarjetas")
    public String pago(Authentication aut, Model model) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero las tarjetas del usuario.
        List<Tarjeta> tarjetas = usuario.getTarjetas();

        // Envío las tarjetas a la vista.
        model.addAttribute("tarjetas", tarjetas);
        return "tarjetas";

    }


    /**
     * Método que recibe una tarjeta a través de un formulario y la añade a la base de datos.
     */
    @PostMapping("/anyadirTarjeta/{origen}")
    public String anyadirTarjeta(Tarjeta tarjeta, @PathVariable("origen") String origen, Authentication aut,
                                 Model model, RedirectAttributes flash) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Añado la tarjeta al usuario.
        usuario.addTarjeta(tarjeta);

        // Actualizo el usuario en la BBDD.
        if (usuarioService.actualizarUsuario(usuario)) {
            // Envío un mensaje de éxito.
            flash.addFlashAttribute("mensajeOk", "Tarjeta añadida correctamente.");
            // Actualizo la lista de tarjetas del usuario para la vista.
            List<Tarjeta> tarjetas = usuario.getTarjetas();
            model.addAttribute("tarjetas", tarjetas);
            switch (origen) {
                case "pedido":
                    return "redirect:/tarjetaEnvioPedido";
                case "tarjeta":
                    return "redirect:/tarjetas";
                default:
                    return "redirect:/tarjetas";
            }
        } else {
            // Envío un mensaje de error.
            flash.addFlashAttribute("mensajeError", "Error al añadir la tarjeta.");
            switch (origen) {
                case "pedido":
                    return "redirect:/tarjetaEnvioPedido";
                case "tarjeta":
                    return "redirect:/tarjetas";
                default:
                    return "redirect:/tarjetas";
            }
        }

    }


    /**
     * Este método recibe el id de una tarjeta y la elimina de la base de datos.
     */
    @PostMapping("/eliminarTarjeta/{id}/{origen}")
    public String eliminarTarjeta(@PathVariable(name = "id") int idTarjeta, @PathVariable("origen") String origen,
                                  Authentication aut, Model model, RedirectAttributes flash) {

        // Recupero el usuario.
        Usuario usuario = new Usuario();
        if (aut != null) usuario = usuarioService.buscarPorEmail(aut.getName());

        // Recupero la tarjeta.
        Tarjeta tarjeta = tarjetaService.buscarTarjetaPorId(idTarjeta);

        // Elimino la tarjeta del usuario.
        usuario.removeTarjeta(tarjeta);

        // Actualizo el usuario en la BBDD.
        if (usuarioService.actualizarUsuario(usuario)) {
            // Si es ok, se habrá eliminado la relación, ahora elimino la tarjeta.
            if (tarjetaService.eliminarTarjeta(tarjeta.getIdTarjeta())) {
                // Envío un mensaje de éxito.
                flash.addFlashAttribute("mensajeOk", "Tarjeta eliminada correctamente.");
                // Actualizo la lista de tarjetas del usuario para la vista.
                List<Tarjeta> tarjetas = usuario.getTarjetas();
                model.addAttribute("tarjetas", tarjetas);
                switch (origen) {
                    case "pedido":
                        return "redirect:/tarjetaEnvioPedido";
                    case "tarjeta":
                        return "redirect:/tarjetas";
                    default:
                        return "redirect:/tarjetas";
                }
            } else {
                // Envío un mensaje de error.
                flash.addFlashAttribute("mensajeError",
                        "Error al eliminar la tarjeta. Se ha eliminado la relación, pero no la tarjeta.");
                switch (origen) {
                    case "pedido":
                        return "redirect:/tarjetaEnvioPedido";
                    case "tarjeta":
                        return "redirect:/tarjetas";
                    default:
                        return "redirect:/tarjetas";
                }
            }
        } else {
            flash.addFlashAttribute("mensajeError", "Error al eliminar la tarjeta.");
            switch (origen) {
                case "pedido":
                    return "redirect:/tarjetaEnvioPedido";
                case "tarjeta":
                    return "redirect:/tarjetas";
                default:
                    return "redirect:/tarjetas";
            }
        }

    }


    /**
     * Este método recibe el id de una tarjeta y la envía a la vista para que se pueda editar.
     */
    @GetMapping("/editarTarjeta/{id}")
    public String editarDireccion(@PathVariable("id") int idTarjeta, Model model, RedirectAttributes flash) {

        // Recupero la tarjeta.
        Tarjeta tarjeta = tarjetaService.buscarTarjetaPorId(idTarjeta);

        // Envío la tarjeta a la vista.
        model.addAttribute("tarjeta", tarjeta);
        return "editarTarjeta";

    }


    /**
     * Este método recibe una tarjeta editada y la actualiza en la base de datos.
     */
    @PostMapping("/editarTarjeta/{id}")
    public String editarDireccion(@PathVariable("id") int idTarjeta, Tarjeta tarjeta, RedirectAttributes flash) {

        // Le asigno el id de la tarjeta que se va a editar.
        tarjeta.setIdTarjeta(idTarjeta);

        // Actualizo la dirección en la BBDD.
        if (tarjetaService.actualizarTarjeta(tarjeta)) {
            // Envío un mensaje de éxito.
            flash.addFlashAttribute("mensajeOk", "Tarjeta editada correctamente.");
            return "redirect:/tarjetas";
        } else {
            // Envío un mensaje de error.
            flash.addFlashAttribute("mensajeError", "Error al editar la tarjeta.");
            return "redirect:/tarjetas";
        }

    }


    /**
     * Este método es para las fechas.
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
