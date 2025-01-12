package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT obj FROM Movie obj " +
            "INNER JOIN  obj.genre gen " +
            "WHERE COALESCE(:genres) IS NULL OR gen in :genres " +
            "ORDER BY title ")
    Page<Movie> find(List<Genre> genres, Pageable pageable);
}
