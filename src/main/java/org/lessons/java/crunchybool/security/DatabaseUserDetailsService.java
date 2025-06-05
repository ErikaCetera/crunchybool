package org.lessons.java.crunchybool.security;


import java.util.Optional;

import org.lessons.java.crunchybool.model.User;
import org.lessons.java.crunchybool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> userAttempt = userRepository.findByUsername(username);

    if(userAttempt.isEmpty()){
        throw new UsernameNotFoundException("Utente non trovato: " + username);
    }
    return new DatabaseUserDetails(userAttempt.get());
    }
    
}
