package com.devraphael.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devraphael.movieflix.dto.GenreDTO;
import com.devraphael.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

	@Autowired
	private GenreService service;
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR', 'ROLE_MEMBER')")
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() { 
		List<GenreDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}


}
