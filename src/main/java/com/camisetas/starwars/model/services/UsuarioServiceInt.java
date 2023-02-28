package com.camisetas.starwars.model.services;
import com.camisetas.starwars.model.entity.Usuario;

public interface UsuarioServiceInt {

    boolean crearUsuario(Usuario usuario);
    boolean comprobarEdad(Usuario usuario);
    Usuario buscarPorEmail(String email);
    boolean actualizarUsuario(Usuario usuario);

}
