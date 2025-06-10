package org.lessons.java.crunchybool.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.model.Review;
import org.lessons.java.crunchybool.repository.AnimeRepository;
import org.lessons.java.crunchybool.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // restituisce tutti gli anime
    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    // ordina in ordine in base al titolo
    public List<Anime> findAllSorteByName() {
        return animeRepository.findAll(Sort.by("title"));
    }

    // cerca un oggetto anime in base all'id
    public Optional<Anime> findById(Integer id) {
        return animeRepository.findById(id);
    }

    // restituisce un oggetto anime in base all'id
    public Anime getById(Integer id) {
        Optional<Anime> animeAttempt = animeRepository.findById(id);
        if (animeAttempt.isEmpty()) {
            throw new EntityNotFoundException("Anime non trovato!");
        }
        return animeAttempt.get();
    }

    // cerca per titolo e titolo originale
    public List<Anime> findByName(String name) {
        return animeRepository.findByTitleContainingIgnoreCaseOrOriginalTitleContainingIgnoreCase(name, name);
    }
    
    public List<Anime> findByGenre(String genreName) {
    return animeRepository.findByGenres_NameIgnoreCase(genreName);
}


    // crea nuovo anime
    public Anime create(Anime anime) {
        return animeRepository.save(anime);
    }

    // aggiorna anime
    public Anime update(Anime anime) {
        return animeRepository.save(anime);
    }

    // Elimina anime, comprese recensioni associate
    public void deleteById(Integer id) {
        Anime anime = getById(id);

        for (Review reviewToDelete : anime.getReviews()) {
            reviewRepository.delete(reviewToDelete);
        }

        animeRepository.delete(anime);
    }

    public void delete(Anime anime) {
        deleteById(anime.getId());
    }

    
    public Boolean existsById(Integer id) {
        return animeRepository.existsById(id);
    }

    public Boolean exists(Anime anime) {
        return existsById(anime.getId());
    }

}
