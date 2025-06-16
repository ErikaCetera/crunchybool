package org.lessons.java.crunchybool.model;

import java.util.List;



import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "animes")
public class Anime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Non può essere nullo, vuoto o contenere solo spazi.")
    private String title;

    @NotBlank(message = "Non può essere nullo, vuoto o contenere solo spazi.")
    private String originalTitle;

    @NotBlank(message = "Non può essere nullo, vuoto o contenere solo spazi.")
    private String production;

    @NotBlank(message = "Non può essere nullo, vuoto o contenere solo spazi.")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Min(value = 1, message = "deve avere almeno un episodio.")
    private Integer episode;

    @Min(value = 1, message = "deve avere almeno una stagione.")
    private Integer seasons;

    private String image;

    //relazione con recensioni
    @OneToMany(mappedBy = "anime")
    
    private List<Review> reviews;

    //relazione con generi
    @ManyToMany
    @JoinTable(
        name = "anime_genre",
        joinColumns = @JoinColumn( name = "anime_id"),
        inverseJoinColumns = @JoinColumn( name = "genre_id")
        )
    
    private List<Genre> genres;



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getProduction() {
        return production;
    }
    public void setProduction(String production) {
        this.production = production;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getEpisode() {
        return episode;
    }
    public void setEpisode(Integer episode) {
        this.episode = episode;
    }
    public Integer getSeasons() {
        return seasons;
    }
    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
