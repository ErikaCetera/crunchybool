package org.lessons.java.crunchybool.controller;

import java.util.List;

import org.lessons.java.crunchybool.model.Review;
import org.lessons.java.crunchybool.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    //Restituisce la lista di tutte le recensioni.
    @GetMapping
    public List<Review> index() {
        return reviewService.findAll();
    }
    
    //Crea e salva una nuova recensione nel database
    @PostMapping
    public ResponseEntity<Review> store(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.create(review), HttpStatus.CREATED);
    }

    //Elimina una recensione se esiste
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (reviewService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
