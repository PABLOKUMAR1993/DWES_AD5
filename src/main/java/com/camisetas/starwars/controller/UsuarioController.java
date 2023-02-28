package com.camisetas.starwars.controller;
import com.camisetas.starwars.model.entity.Role;
import com.camisetas.starwars.model.entity.Usuario;
import com.camisetas.starwars.model.services.RoleServiceInt;
import com.camisetas.starwars.model.services.UsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class UsuarioController {


    // Atributos

    @Autowired
    private UsuarioServiceInt usuarioService;

    @Autowired
    private RoleServiceInt roleService;


    // Métodos Implementados


    /**
     * Método que crea un usuario CLIENTE.
     * Recibe el usuario por parámetro:
     * 1º comprueba si es mayor de edad.
     * 2º comprueba si el email ya existe (el mismo método que crea el usuario).
     * Si ambas condiciones son validas, se crea el usuario.
     *
     * @param usuario Objeto usuario creado automaticamente al enviar el formulario.
     * @return devuelve a /login si se ha creado correctamente, /registro si no.
     */
    @PostMapping("/crearCliente")
    public String crearCliente(Model model, RedirectAttributes redirect, Usuario usuario) {

        // Le agregamos por defecto el rol de cliente
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.buscarPorId(1));
        usuario.setRoles(roles);

        // Comprobamos si el usuario es mayor de edad
        if (usuarioService.comprobarEdad(usuario)) {
            // Tratamos de crear e usuario comprobando previamente su duplicidad
            if (usuarioService.crearUsuario(usuario)) {
                redirect.addFlashAttribute("mensajeLogin", "Usuario creado correctamente, puedes loggearte");
                redirect.toString();
                return "redirect:/login";
            } else {
                model.addAttribute("mensajeEmail", "Usuario no creado porque este email ya existe");
                return "registro";
            }
        } else {
            model.addAttribute("mensajeEdad", "Usuario no creado porque no es mayor de edad");
            return "registro";
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
