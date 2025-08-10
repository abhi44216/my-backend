package com.example.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class Movie {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String title;
    private String description;
    private String posterUrl;
    private String trailerUrl;
    private double rating;
    private List<String> genres = new ArrayList<>();

    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
