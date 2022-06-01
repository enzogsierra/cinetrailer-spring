package com.example.cinetrailer.repository;

import java.util.List;

import com.example.cinetrailer.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> 
{
    @Query(value = "SELECT movies.* FROM movies JOIN genre_movie ON movies.id = movies_id AND genres_id = ?1", nativeQuery = true)
    List<Movie> findByGenre(Integer id);
}
