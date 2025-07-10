package com.devraphael.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devraphael.movieflix.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
