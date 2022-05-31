package com.example.cinetrailer.controller;

import java.util.List;

import com.example.cinetrailer.model.Genre;
import com.example.cinetrailer.model.Movie;
import com.example.cinetrailer.repository.GenreRepository;
import com.example.cinetrailer.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
        List<Movie> movies = movieRepository.findAll(PageRequest.of(0, 4, Sort.by("releaseDate").descending())).toList();

        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movies")
    public String movies(@PageableDefault(sort = "releaseDate", direction = Sort.Direction.DESC) Pageable pageable, Model model)
    {
        Page<Movie> movies = movieRepository.findAll(pageable);

        model.addAttribute("movies", movies);
        return "";
    }

    @GetMapping("/movie/{id}")
    public String movie(@PathVariable Integer id, Model model)
    {
        Movie movie = movieRepository.findById(id).get();

        model.addAttribute("movie", movie);
        return "movie";
    }
}
