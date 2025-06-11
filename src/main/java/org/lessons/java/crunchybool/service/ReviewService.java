package org.lessons.java.crunchybool.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Review;
import org.lessons.java.crunchybool.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

    @Autowired
    private  ReviewRepository reviewRepository;

    // Restituisce la lista di tutte le recensioni presenti nel database
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    // Cerca una recensione per ID
    public Optional<Review> findById(Integer id) {
        return reviewRepository.findById(id);
    }

     // Crea una nuova recensione e la salva nel database
    public Review create(Review offer) {
        return reviewRepository.save(offer);
    }

    // Aggiorna una recensione
    public Review update(Review offer) {
        return reviewRepository.save(offer);
    }

    // Recupera una recensione per ID
    public Review getById(Integer id) {
        Optional<Review> reviewAttempt = reviewRepository.findById(id);
        if (reviewAttempt.isEmpty()) {
            throw new EntityNotFoundException("Recensione non trovata!");
        }
        return reviewAttempt.get();
    }

    // Calcola il voto medio delle recensioni per un determinato anime
    public Integer getAverageRating(Integer animeId) {
        return reviewRepository.findAverageRatingByAnimeId(animeId);
    }

    // Elimina una recensione dal database per ID
    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

    // Elimina una recensione utilizzando il suo ID
    public void delete(Review review) {
        deleteById(review.getId());

    }

}
