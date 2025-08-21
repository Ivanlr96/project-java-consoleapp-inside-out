package dev.ivan.views;

import dev.ivan.models.movie.Movie;

import java.util.List;

public class AllMoviesView {

    public static void display(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No se ha encontrado ninguna película.");
        } else {
            System.out.println("--- Listado de películas ---");
            for (Movie movie : movies) {
                System.out.println(movie);
                System.out.println("--------------------");
            }
        }
    }
}
