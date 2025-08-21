package dev.ivan.controllers;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;
import dev.ivan.repositories.MovieCSVRepository;
import dev.ivan.services.MovieService;
import dev.ivan.views.AllMoviesView;
import dev.ivan.views.MoviePostView;

import java.util.List;

public class MovieController {

    private final MovieService movieService;
    private final MovieCSVRepository movieCSVRepository;
    private final MoviePostView moviePostView;

    public MovieController() {
        this.movieService = new MovieService();
        this.movieCSVRepository = new MovieCSVRepository();
        this.moviePostView = new MoviePostView();
    }

    public void addMovie() {
        String[] movieData = moviePostView.display();
        String imdbId = movieData[0];
        EmotionEnum emotion = EmotionEnum.valueOf(movieData[1]);

        Movie movie = movieService.getMovie(imdbId, emotion);
        movieCSVRepository.save(movie);

        System.out.println("Pelicula Añadida con éxito:");
        System.out.println(movie);

    }

    public void showAllMovies() {
        List<Movie> movies = movieCSVRepository.findAll();
        AllMoviesView.display(movies);
    }
}
