package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO isert(ReviewDTO dto) {

        User user = authService.authenticated();

        try {
            Review entity = new Review();
            entity.setMovie(movieRepository.getOne(dto.getMovieId()));
            entity.setUser(user);
            entity.setText(dto.getText());
            repository.save(entity);

            return new ReviewDTO(entity);
        } catch (Exception e) {
            throw new ForbiddenException(e.getMessage());
        }
    }


}
