package com.example.demo.resource;



import com.example.demo.entity.Director;
import com.example.demo.entity.Movie;
//import com.example.demo.entity.Product;

import com.example.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/products")
@Controller
public class MovieController {



    @QueryMapping(name = "findMovieById")
    public Movie getMovie(@Argument String id){
         return Movie.getMovieById(id) ;
    }
    @SchemaMapping
    public Director director(Movie movie) {
        return Director.getById(movie.director());
    }

    @MutationMapping
    public Movie addReview(@Argument String movieId,
                           @Argument String comment,
                           @Argument Integer rating) {

        Movie movie = Movie.getMovieById(movieId);
        if (movie == null) {
            return null; // or throw GraphQL error later
        }

        // copy existing reviews (avoid mutating the original list)
        List<Review> updatedReviews = new ArrayList<>(movie.reviews());
        updatedReviews.add(new Review(comment, rating));

        // create updated Movie (records are immutable)
        Movie updatedMovie = new Movie(movie.id(), movie.title(), movie.genre(), movie.director(), updatedReviews);

        // replace in "database"
        Movie.replaceMovie(updatedMovie);

        // Task 3: return updated Movie
        return updatedMovie;
    }





////    @GetMapping
//    @QueryMapping
//    public List<Product> getProducts() {
//        return service.getProducts();
//    }
//
//    @QueryMapping
//    public List<Product> getProductsByCategory(@Argument String category) {
//        return service.getProductsByCategory(category);
//    }
//
//
//    @MutationMapping
//    public Product updateStock(@Argument int id, @Argument int stock) {
//        return service.updateStock(id, stock);
//
//    }
//
//    @MutationMapping
//    @SubscriptionMapping
//    public Product receiveNewShipment(@Argument int id, @Argument int quantity) {
//        return service.receiveNewShipment(id, quantity);
//
//    }
}
