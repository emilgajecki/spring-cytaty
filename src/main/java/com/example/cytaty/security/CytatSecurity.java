package com.example.cytaty.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

//konfiguracja
@Configuration
public class CytatSecurity extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/cytaty").permitAll()
//                .antMatchers(HttpMethod.POST, "/cytaty").hasRole("MODERATOR")
//                .antMatchers(HttpMethod.DELETE, "/cytaty").hasRole("ADMIN")
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                //wylaczenie bledu z postmana
//                .csrf().disable();
//
//    }

    //metoda do pobierania danych z bazy danych

    //wczytuje bean do zmiennej
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .usersByUsernameQuery("select name, password from dbo.user")
//                .authoritiesByUsernameQuery("select role form dbo.user")
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);
//    }



    //dodawanie uzytkownikow
    @Bean
    public UserDetailsService userDetailsService() {
        // metoda withDefaultPasswordEncoder - nie ma kodowanego hasla
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("moderator")
                .password("moderator1")
                .roles("MODERATOR")
                .build();

        return new InMemoryUserDetailsManager(admin, moderator);
    }
}
