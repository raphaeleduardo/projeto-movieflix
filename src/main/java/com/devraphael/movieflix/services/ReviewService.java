package com.devraphael.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devraphael.movieflix.dto.ReviewDTO;
import com.devraphael.movieflix.entities.Review;
import com.devraphael.movieflix.entities.User;
import com.devraphael.movieflix.projections.ReviewProjection;
import com.devraphael.movieflix.repositories.MovieRepository;
import com.devraphael.movieflix.repositories.ReviewRepository;
import com.devraphael.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private AuthService auth;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long movieId) {
		if (!movieRepository.existsById(movieId)) {
			throw new ResourceNotFoundException("Id not found " + movieId);
		}
		List<ReviewProjection> list = repository.searchByMovie(movieId);
		return list.stream().map(x -> new ReviewDTO(x)).toList();
	}

	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		User user = auth.authenticated();

		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
		entity.setUser(user);
	}

}