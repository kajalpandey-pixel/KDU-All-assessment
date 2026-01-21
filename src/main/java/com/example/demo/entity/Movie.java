package com.example.demo.entity ;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String name;
//    private String category;
//    private Float price;
//    private Integer stock;
//
//    public Product(String name, String category, Float price, Integer stock) {
//        this.name = name;
//        this.category = category;
//        this.price = price;
//        this.stock = stock;
//    }
//}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Movie(String id, String title, String genre, String director ,List<Review> reviews) {

    private static List<Movie> movies = Arrays.asList(
            new Movie("movie-1", "Harry Potter", "Adventure", "Director-1" , new ArrayList<>()),
            new Movie("movie-2", "Moby Dick", "Comedy", "Director-2" , new ArrayList<>()),
            new Movie("movie-3", "Interview", "mystery", "Director-3" , new ArrayList<>())
    );

    public static Movie getMovieById(String id) {
        return movies.stream()
                .filter(movie -> movie.id().equals(id)) // FIXED HERE
                .findFirst()
                .orElse(null);
    }


    public static void replaceMovie(Movie updated) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).id().equals(updated.id())) {
                movies.set(i, updated);
                return;
            }
        }
    }
}









