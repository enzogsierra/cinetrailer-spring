package com.example.cinetrailer.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.cinetrailer.model.Genre;
import com.example.cinetrailer.model.Movie;
import com.example.cinetrailer.repository.GenreRepository;
import com.example.cinetrailer.repository.MovieRepository;
import com.example.cinetrailer.service.StorageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController 
{
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StorageServiceImpl storageService;


    @GetMapping("/")
    public String index(@PageableDefault(sort = "title", size = 5) Pageable pageable, Model model)
    {
        Page<Movie> movies = movieRepository.findAll(pageable);
        model.addAttribute("movies", movies);
        return "admin/index";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model)
    {
        List<Genre> genres = genreRepository.findAll(Sort.by("title"));

        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genres);
        return "admin/addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie_POST(@Valid Movie movie, BindingResult result, Model model)
    {
        
        return "redirect:/admin/";
    }
}
