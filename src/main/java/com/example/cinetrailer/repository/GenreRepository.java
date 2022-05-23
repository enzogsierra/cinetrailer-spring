package com.example.cinetrailer.repository;

import com.example.cinetrailer.model.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    
}
