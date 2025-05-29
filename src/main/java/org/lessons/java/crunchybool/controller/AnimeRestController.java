package org.lessons.java.crunchybool.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animes")
public class AnimeRestController {

    @Autowired
    private AnimeService animeService;

    @GetMapping
    public List<Anime> index() {
        return animeService.findAllSorteByName();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> show(@PathVariable Integer id) {
        Optional<Anime> anime = animeService.findById(id);
        if (anime.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(anime.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Anime> store(@RequestBody Anime anime) {
        return new ResponseEntity<>(animeService.create(anime), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anime> update(@PathVariable Integer id, @RequestBody Anime anime) {
        if (animeService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        anime.setId(id);
        return new ResponseEntity<>(animeService.update(anime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (animeService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

