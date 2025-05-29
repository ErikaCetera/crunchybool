package org.lessons.java.crunchybool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import java.util.List;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.model.Review;
import org.lessons.java.crunchybool.service.AnimeService;
import org.lessons.java.crunchybool.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeService animeService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String index(Model model) {
        List<Anime> animes = animeService.findAllSorteByName();

        model.addAttribute("animes", animes);
        return "animes/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Anime anime = animeService.getById(id);
        Integer averageRating = reviewService.getAverageRating(anime.getId());


        model.addAttribute("anime", anime);
        model.addAttribute("genres", anime.getGenres());
        model.addAttribute("averageRating", averageRating);
        return "animes/show";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Anime> animes = animeService.findByName(name);
        model.addAttribute("animes", animes);
        return "animes/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("anime", new Anime());

        return "animes/create-or-edit";
    }

    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("anime") Anime formAnime, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "animes/create-or-edit";
        }
        animeService.create(formAnime);

        return "redirect:/animes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("anime", animeService.getById(id));

        return "animes/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("pizza") Anime formAnime, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "animes/create-or-edit";
        }
        animeService.update(formAnime);
        return "redirect:/animes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Anime anime = animeService.getById(id);
        animeService.delete(anime);
        return "redirect:/animes";
    }

    // Metodo per mostrare il form di creazione/modifica di una recensione di un
    // anime specifico
    @GetMapping("/{id}/review")
    public String review(@PathVariable Integer id, Model model) {
        Review review = new Review();
        // Collega l'offerta alla pizza selezionata
        review.setAnime(animeService.getById(id));
        model.addAttribute("review", review);
        return "reviews/create-or-edit";
    }

}
