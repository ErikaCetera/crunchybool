package org.lessons.java.crunchybool.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AnimeService animeService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("review") Review formReview, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/animes" + (formReview.getAnime() != null ? formReview.getAnime().getId() : "");
        }

        Anime anime = animeService.getById(formReview.getAnime().getId());
        formReview.setAnime(anime);

        reviewService.create(formReview);
        return "redirect:/animes/" + anime.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("review", reviewService.getById(id));
        model.addAttribute("edit", true);
        return "/reviews/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("review") Review formReview, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "/reviews/create-or-edit";
        }
        reviewService.update(formReview);
        return "redirect:/animes/" + formReview.getAnime().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Review review = reviewService.getById(id);
        reviewService.delete(review);
        return "redirect:/animes/" + review.getAnime().getId();
    }

}
