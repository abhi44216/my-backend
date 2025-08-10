package com.example.movies.controller;

import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
        Optional<Movie> movie = movieRepository.findById(new ObjectId(id));
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/review")
    public ResponseEntity<Movie> addReview(@PathVariable("id") String id, @RequestBody Review review) {
        Optional<Movie> optionalMovie = movieRepository.findById(new ObjectId(id));
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.getReviews().add(review);
            movieRepository.save(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
