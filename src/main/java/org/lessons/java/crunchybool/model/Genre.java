package org.lessons.java.crunchybool.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name= "genres")
public class Genre{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Non può essere nullo, vuoto o contenere solo spazi.")
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private List<Anime> animes;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Anime> getAnimes() {
        return animes;
    }
    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }


}