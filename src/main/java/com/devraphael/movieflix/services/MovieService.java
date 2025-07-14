package com.devraphael.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devraphael.movieflix.dto.MovieCardDTO;
import com.devraphael.movieflix.dto.MovieDetailsDTO;
import com.devraphael.movieflix.entities.Genre;
import com.devraphael.movieflix.entities.Movie;
import com.devraphael.movieflix.repositories.GenreRepository;
import com.devraphael.movieflix.repositories.MovieRepository;
import com.devraphael.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
		return new MovieDetailsDTO(entity, entity.getGenre());
	}

	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findByGenre(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getReferenceById(genreId);
		Page<Movie> page = repository.searchByGenre(genre, pageable);
		return page.map(x -> new MovieCardDTO(x));
	}

}