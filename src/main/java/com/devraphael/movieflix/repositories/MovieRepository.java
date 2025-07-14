package com.devraphael.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devraphael.movieflix.entities.Genre;
import com.devraphael.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE :genre IS NULL OR obj.genre = :genre ORDER BY obj.title", 
			countQuery = "SELECT COUNT(obj) FROM Movie obj JOIN obj.genre WHERE :genre IS NULL OR obj.genre = :genre")
	Page<Movie> searchByGenre(Genre genre, Pageable pageable);
	
}
