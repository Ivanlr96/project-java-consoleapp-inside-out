package dev.ivan.controllers;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;
import dev.ivan.repositories.MovieCSVRepository;
import dev.ivan.services.MovieService;
import dev.ivan.views.HomeView;
import dev.ivan.views.movie.AllMoviesView;
import dev.ivan.views.movie.MovieDeleteView;
import dev.ivan.views.movie.MovieFilterByGenreView;
import dev.ivan.views.movie.MoviePostView;

import java.util.List;

public class MovieController {

    private final MovieService movieService;
    private final MovieCSVRepository movieCSVRepository;
    private final MoviePostView moviePostView;
    private final MovieDeleteView movieDeleteView;
    private final MovieFilterByGenreView movieFilterByGenreView;

    public MovieController() {
        this.movieService = new MovieService();
        this.movieCSVRepository = new MovieCSVRepository();
        this.moviePostView = new MoviePostView();
        this.movieDeleteView = new MovieDeleteView();
        this.movieFilterByGenreView = new MovieFilterByGenreView();
    }

    public void addMovie() {
        String[] movieData = moviePostView.display();
        String imdbId = movieData[0];
        EmotionEnum emotion = EmotionEnum.valueOf(movieData[1]);

        Movie movie = movieService.getMovie(imdbId, emotion);
        if (movie != null) {
            movieCSVRepository.save(movie);
            moviePostView.printSuccess(movie);
        } else {
            moviePostView.printError();
        }
    }

    public void showAllMovies() {
        List<Movie> movies = movieCSVRepository.findAll();
        AllMoviesView.display(movies);
        HomeView.printMenu();
    }

    public void deleteMovie() {
        List<Movie> movies = movieCSVRepository.findAll();
        if (movies.isEmpty()) {
            movieDeleteView.printNoMoviesToDelete();
            return;
        }
        AllMoviesView.display(movies);
        int index = movieDeleteView.display();

        if (index > 0 && index <= movies.size()) {
            Movie movieToDelete = movies.get(index - 1);
            movieCSVRepository.delete(movieToDelete.getImdbId());
            movieDeleteView.printSuccess();
        } else {
            movieDeleteView.printInvalidIndex();
        }
    }

    public void showMoviesByGenre() {
        String genre = movieFilterByGenreView.display();
        List<Movie> movies = movieCSVRepository.findByGenre(genre);
        AllMoviesView.display(movies);
    }

}
