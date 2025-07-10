package com.devraphael.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devraphael.movieflix.dto.GenreDTO;
import com.devraphael.movieflix.entities.Genre;
import com.devraphael.movieflix.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;
	
	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		List<Genre> list = repository.findAll();
		return list.stream().map(x -> new GenreDTO(x)).toList();
	}

	
}
