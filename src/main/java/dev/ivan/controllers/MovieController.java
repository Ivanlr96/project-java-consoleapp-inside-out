package dev.ivan.controllers;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;
import dev.ivan.repositories.MovieCSVRepository;
import dev.ivan.services.MovieService;
import dev.ivan.views.AllMoviesView;
import dev.ivan.views.MovieDeleteView;
import dev.ivan.views.MoviePostView;

import java.util.List;

public class MovieController {

    private final MovieService movieService;
    private final MovieCSVRepository movieCSVRepository;
    private final MoviePostView moviePostView;
    private final MovieDeleteView movieDeleteView;

    public MovieController() {
        this.movieService = new MovieService();
        this.movieCSVRepository = new MovieCSVRepository();
        this.moviePostView = new MoviePostView();
        this.movieDeleteView = new MovieDeleteView();
    }

    public void addMovie() {
        String[] movieData = moviePostView.display();
        String imdbId = movieData[0];
        EmotionEnum emotion = EmotionEnum.valueOf(movieData[1]);

        Movie movie = movieService.getMovie(imdbId, emotion);
        movieCSVRepository.save(movie);

        System.out.println("Pelicula añadida con éxito:");
        System.out.println(movie);
    }

    public void showAllMovies() {
        List<Movie> movies = movieCSVRepository.findAll();
        AllMoviesView.display(movies);
    }

    public void deleteMovie() {
        List<Movie> movies = movieCSVRepository.findAll();
        if (movies.isEmpty()) {
            System.out.println("No hay películas para eliminar.");
            return;
        }
        AllMoviesView.display(movies);
        int index = movieDeleteView.display();

        if (index > 0 && index <= movies.size()) {
            Movie movieToDelete = movies.get(index - 1);
            movieCSVRepository.delete(movieToDelete.getImdbId());
            System.out.println("Pelicula eliminada con éxito.");
        } else {
            System.out.println("Índice inválido.");
        }
    }
}
