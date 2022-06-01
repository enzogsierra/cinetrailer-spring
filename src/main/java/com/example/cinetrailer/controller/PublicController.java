package com.example.cinetrailer.controller;

import java.util.List;

import com.example.cinetrailer.model.Genre;
import com.example.cinetrailer.model.Movie;
import com.example.cinetrailer.repository.GenreRepository;
import com.example.cinetrailer.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PublicController 
{
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;


    @GetMapping(value = {"", "/"})
    public String index(Model model)
    {
        List<Movie> movies = movieRepository.findAll(PageRequest.of(0, 8, Sort.by("releaseDate").descending())).toList();

        model.addAttribute("movies", movies);
        return "public/index";
    }

    @GetMapping("/movies")
    public String movies(@RequestParam(defaultValue = "0") String genre, Model model) throws NumberFormatException
    {
        // Get genre id filter
        Integer genreId = 0;
        try { 
            genreId = Integer.parseInt(genre); 
        }
        catch(NumberFormatException e) { 
            genreId = 0; 
        }

        List<Movie> movies;
        List<Genre> genres = genreRepository.findAll();

        // Get movies list
        if(genreId == 0) movies = movieRepository.findAll(Sort.by("releaseDate").descending());
        else movies = movieRepository.findByGenre(genreId);

        model.addAttribute("movies", movies);
        model.addAttribute("genres", genres);
        return "public/movies";
    }

    @GetMapping("/movie/{id}")
    public String movie(@PathVariable Integer id, Model model)
    {
        Movie movie = movieRepository.findById(id).get();

        model.addAttribute("movie", movie);
        return "public/movie";
    }
}
