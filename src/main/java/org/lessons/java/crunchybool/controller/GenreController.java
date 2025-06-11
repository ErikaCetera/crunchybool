package org.lessons.java.crunchybool.controller;

import org.lessons.java.crunchybool.model.Genre;
import org.lessons.java.crunchybool.service.GenreService;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    //Recupera la lista di tutti i generi e li passa alla vista
    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres/index";
    }

    //Mostra il form per creare un nuovo genere
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/create";
    }

    //Salva il genere dopo la validazione
    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("gener") Genre formGenre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/create-or-edit";
        }
        genreService.create(formGenre);

        return "redirect:/genres";
    }

    // Elimina anime dal db se esite
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }

}
