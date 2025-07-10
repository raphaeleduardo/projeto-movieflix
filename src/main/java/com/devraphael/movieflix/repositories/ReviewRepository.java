package com.devraphael.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devraphael.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
