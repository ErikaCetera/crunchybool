package org.lessons.java.crunchybool.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Review;
import org.lessons.java.crunchybool.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    
    @Autowired ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review create(Review offer) {
        return reviewRepository.save(offer);
    }

    public Review update(Review offer) {
        return reviewRepository.save(offer);
    }

    public Review getById(Integer id) {
        Optional<Review> reviewAttempt = reviewRepository.findById(id);
        if (reviewAttempt.isEmpty()) {
            // aggiungere response entity
        }
        return reviewAttempt.get();
    }

    public void deleteById(Integer id) {
        reviewRepository.deleteById(id);
    }

    public void delete(Review review) {
        deleteById(review.getId());

    }

}
