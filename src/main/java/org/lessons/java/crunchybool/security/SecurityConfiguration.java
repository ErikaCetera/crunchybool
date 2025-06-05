package org.lessons.java.crunchybool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/animes/create").hasRole( "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/reviews/**", "/animes/**").hasRole("ADMIN")
                        .requestMatchers("/genres","/genres/**").hasRole("ADMIN")
                        .requestMatchers("/animes", "/animes/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll());

        return http.build();
    }

}
