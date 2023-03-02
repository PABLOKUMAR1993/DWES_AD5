package com.camisetas.starwars.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {


    // Métodos


    /**
     * Método que devuelve la vista login.jsp
     */
    @GetMapping("/login")
    public String login() { return "login"; }


    /**
     * Método que devuelve la vista registro.jsp
     */
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }


}
