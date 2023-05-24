package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query(value = "SELECT new com.devsuperior.movieflix.dto.MovieDTO(obj.id, obj.title, obj.subTitle, obj.year, obj.imgUrl) FROM Movie obj "
				 + "INNER JOIN obj.genre objGenre "
				 + "WHERE "
				 + "(:genre IS NULL OR :genre = objGenre)")
	Page<MovieDTO> find(Genre genre, Pageable pageable);
}