package com.devraphael.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devraphael.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
