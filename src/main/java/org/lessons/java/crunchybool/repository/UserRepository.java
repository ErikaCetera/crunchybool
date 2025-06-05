package org.lessons.java.crunchybool.repository;



import java.util.Optional;

import org.lessons.java.crunchybool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

Optional<User> findByUsername (String username);
    
}
