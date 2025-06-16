package org.lessons.java.crunchybool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.service.AnimeService;
import org.lessons.java.crunchybool.service.ReviewService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animes")
public class AnimeRestController {

    @Autowired
    private AnimeService animeService;

    @Autowired
    private ReviewService reviewService;

    // Recupera lista degli anime ordinata
    @GetMapping
    public List<Anime> index() {
        return animeService.findAllSorteByName();
    }

    // Cerca per titolo
    @GetMapping("/search")
    public ResponseEntity<List<Anime>> searchByName(@RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(animeService.findAllSorteByName(), HttpStatus.OK);
        }

        List<Anime> animes = animeService.findByName(name);
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }

    // Filtra per genere
    @GetMapping("/searchByGenre")
    public ResponseEntity<List<Anime>> searchByGenre(@RequestParam(name = "genre", required = false) String genreName) {
        if (genreName == null || genreName.isEmpty()) {
            return new ResponseEntity<>(animeService.findAllSorteByName(), HttpStatus.OK);
        }

        List<Anime> animes = animeService.findByGenre(genreName);
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }

    // Restituisce i dettagli di un singolo anime tramite il suo ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Integer id) {
        Optional<Anime> anime = animeService.findById(id);
        Integer averageRating = reviewService.getAverageRating(anime.get().getId());
        if (anime.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("anime", anime.get());
        responseBody.put("averageRating", averageRating);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    // Crea un nuovo anime e lo salva nel database
    @PostMapping
    public ResponseEntity<Anime> store(@RequestBody Anime anime) {
        return new ResponseEntity<>(animeService.create(anime), HttpStatus.CREATED);
    }

    // Aggiorna i dati di un anime esistente
    @PutMapping("/{id}")
    public ResponseEntity<Anime> update(@PathVariable Integer id, @RequestBody Anime anime) {
        if (animeService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        anime.setId(id);
        return new ResponseEntity<>(animeService.update(anime), HttpStatus.OK);
    }

    // Elimina un anime dal database se esiste
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (animeService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
