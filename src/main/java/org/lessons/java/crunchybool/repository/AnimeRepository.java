package org.lessons.java.crunchybool.repository;

import java.util.List;

import org.lessons.java.crunchybool.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository <Anime, Integer>{

public List<Anime> findByTitleContainingIgnoreCaseOrOriginalTitleContainingIgnoreCase(String title, String originalTitle);
public List<Anime> findByGenres_NameIgnoreCase(String genreName);

}
