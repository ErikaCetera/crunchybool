package org.lessons.java.crunchybool.controller;

import java.util.List;

import org.lessons.java.crunchybool.model.Genre;
import org.lessons.java.crunchybool.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    //Restituisce la lista di tutti i generi.
    @GetMapping
    public List<Genre> index() {
        return genreService.findAll();
    }

    //Crea un nuovo genere e lo salva nel database
    @PostMapping
    public ResponseEntity<Genre> store(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.create(genre), HttpStatus.CREATED);
    }

    //Elimina un genere se esiste
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (genreService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
