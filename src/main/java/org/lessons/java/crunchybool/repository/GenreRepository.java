package org.lessons.java.crunchybool.repository;

import java.util.List;


import org.lessons.java.crunchybool.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Integer>{

    public List<Genre> findByNameContainingIgnoreCase(String name);

}
