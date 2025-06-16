package org.lessons.java.crunchybool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Configura la sicurezza HTTP in Spring Security, gestendo autenticazione, autorizzazioni e protezione CSRF
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringRequestMatchers("/api/**")
                .and()
                .httpBasic().and()
                .formLogin().and()
                .logout().and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/animes/create").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/reviews/**", "/animes/**").hasAuthority("ADMIN")
                .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/animes", "/animes/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }

    // Configura il provider di autenticazione usando DAO per recuperare gli utenti
    // dal database
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Carica i dettagli utente utilizzando la classe DatabaseUserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    // Crea un encoder per gestire la sicurezza delle password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
