package org.lessons.java.crunchybool.security;

import java.util.HashSet;
import java.util.Set;

import org.lessons.java.crunchybool.model.Role;
import org.lessons.java.crunchybool.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    // Costruttore che inizializza i dati utente e assegna le autorizzazioni
    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();

        // Per ogni ruolo presente in user.getRoles(), crea un permesso con il nome del
        // ruolo e lo aggiunge a authorities.
        for (Role userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // non scade mai
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // non è mai bloccato
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // le credenziali non scadono
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // sempre abilitato
    @Override
    public boolean isEnabled() {
        return true;
    }

}
