package com.example.cinetrailer.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "movies")
public class Movie 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    

    @NotBlank(message = "Movie title cannot be blank")
    private String title;

    @NotBlank(message = "Movie synopsis cannot be blank")
    private String synopsis;

    @NotNull(message = "You must specify a release date")
    @Future(message = "Release date must be in a future date")
    private LocalDate releaseDate;

    @NotBlank(message = "You must specify a Youtube trailer")
    private String youtubeTrailerUrl;

    @NotEmpty(message = "Select at least 1 genre")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "genre_movie",
        joinColumns = @JoinColumn(name = "movies.id"),
        inverseJoinColumns = @JoinColumn(name = "genres.id")
    )
    private List<Genre> genres;
    
    @Transient
    private MultipartFile cover;

    
    public Movie() {
    }

    public Movie(Integer id, String title, String synopsis, LocalDate releaseDate, String youtubeTrailerUrl, List<Genre> genres, MultipartFile cover) 
    {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.youtubeTrailerUrl = youtubeTrailerUrl;
        this.genres = genres;
        this.cover = cover;
    }


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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getYoutubeTrailerUrl() {
        return youtubeTrailerUrl;
    }

    public void setYoutubeTrailerUrl(String youtubeTrailerUrl) {
        this.youtubeTrailerUrl = youtubeTrailerUrl;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }


    @Override
    public String toString() {
        return "Movie [genres=" + genres + ", id=" + id + ", releaseDate=" + releaseDate + ", synopsis=" + synopsis
                + ", title=" + title + ", youtubeTrailerUrl=" + youtubeTrailerUrl + "]";
    }
}
