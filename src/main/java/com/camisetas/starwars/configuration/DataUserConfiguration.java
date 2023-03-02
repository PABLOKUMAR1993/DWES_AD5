package com.camisetas.starwars.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/login?logout")
            .permitAll();
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


}
