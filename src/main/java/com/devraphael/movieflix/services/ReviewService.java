package com.devraphael.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devraphael.movieflix.dto.ReviewDTO;
import com.devraphael.movieflix.entities.Review;
import com.devraphael.movieflix.entities.User;
import com.devraphael.movieflix.repositories.MovieRepository;
import com.devraphael.movieflix.repositories.ReviewRepository;

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

	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		User user = auth.authenticated();

		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
		entity.setUser(user);
	}

}