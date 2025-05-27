package org.lessons.java.crunchybool.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.lessons.java.crunchybool.model.Anime;
import org.lessons.java.crunchybool.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/")
public class AnimeController {
    
    @Autowired
    private AnimeService animeService;

    @GetMapping
    public String index(Model model) {
        List<Anime> animes =  animeService.findAllSorteByName();
        model.addAttribute("animes", animes);
        return "animes/index";
    }
    
}
