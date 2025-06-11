package org.lessons.java.crunchybool.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.model.Genre;
import org.lessons.java.crunchybool.repository.AnimeRepository;
import org.lessons.java.crunchybool.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GenreService {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Restituisce la lista di tutti i generi 
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    // Restituisce la lista di tutti i generi ordinati
    public List<Genre> findAllSorteByName() {
        return genreRepository.findAll(Sort.by("name"));
    }

    // Cerca un genere per id
    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    // Restituisce un genere per id
    public Genre getById(Integer id) {
        Optional<Genre> genreAttempt = genreRepository.findById(id);
        if (genreAttempt.isEmpty()) {
            throw new EntityNotFoundException("Genere non trovato!");
        }
        return genreAttempt.get();
    }

    // Crea un nuovo genere
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    // Elimina un genere dal database per ID, rimuovendo anche il riferimento dagli anime associati
    public void deleteById(Integer id) {
        Genre genre = getById(id);
        for (Anime anime : genre.getAnimes()) {
            anime.getGenres().remove(genre);
            animeRepository.save(anime);
        }

        genreRepository.deleteById(id);
    }

    public void delete(Genre genre) {
        deleteById(genre.getId());
    }
}
