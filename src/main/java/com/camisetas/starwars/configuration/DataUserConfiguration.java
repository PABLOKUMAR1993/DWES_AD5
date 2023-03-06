package com.camisetas.starwars.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class DataUserConfiguration extends WebSecurityConfigurerAdapter {


    // Atributos


    @Autowired
    private DataSource dataSource;


    // Métodos


    /**
     * Método para gestionar el login con la base de datos.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select email, contrasenya, enabled from usuarios where email = ?")
            .authoritiesByUsernameQuery(
                "select u.email, r.nombre from usuarios_roles ur " +
                "inner join usuarios u on u.id_usuario = ur.id_usuario " +
                "inner join roles r on r.id_rol = ur.id_rol " +
                "where u.email = ?"
            );

    }


    /**
     * Método para gestionar las rutas de la aplicación.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

            // Desactivar la protección CSRF.
            .csrf().disable()
            .authorizeRequests()

            // Las vistas públicas no requieren autenticación.
            .antMatchers("/", "/login", "/logout", "/registro",
                    "/crearCliente", "/catalogo/**", "/carrito").permitAll()

            // Autorizaciones por Roles.
            // Autorizo a todos, porque no hay implemetanción del rol ADMIN.
            .antMatchers("/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_ADMIN")

            // Todas las demás rutas requieren autenticación.
            .anyRequest().authenticated()

            // El formulario de login no requiere autenticación y le doy una nueva vista
            .and().formLogin().loginPage("/login").permitAll();

    }


    /**
     * Método para gestionar los recursos estáticos.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**");
    }


    /**
     * Método para encriptar las contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
