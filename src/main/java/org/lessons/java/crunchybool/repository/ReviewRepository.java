package org.lessons.java.crunchybool.repository;

import org.lessons.java.crunchybool.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT ROUND(AVG(r.rating)) FROM Review r WHERE r.anime.id = :animeId")
    Integer findAverageRatingByAnimeId(@Param("animeId") Integer animeId);

}
