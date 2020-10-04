package com.example.cytaty.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//konfiguracja
@Configuration
public class CytatSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cytaty").permitAll()
                .antMatchers(HttpMethod.POST, "/cytaty").hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/cytaty").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }

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
