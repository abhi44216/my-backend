package com.example.movies.service;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addReview(String movieId, Review review) {
        ObjectId objectId = new ObjectId(movieId);  // This is valid if movieId is a valid 24-char hex string
        Movie movie = movieRepository.findById(objectId).orElse(null);

        if (movie == null) {
            throw new RuntimeException("Movie not found with ID: " + movieId);
        }

        if (movie.getReviews() == null) {
            movie.setReviews(new ArrayList<>());
        }

        movie.getReviews().add(review);
        return movieRepository.save(movie);
    }
}
