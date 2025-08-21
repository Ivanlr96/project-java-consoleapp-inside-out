package dev.ivan.views;

import dev.ivan.models.movie.Movie;

import java.util.List;

public class AllMoviesView {

    public static void display(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No se ha encontrado ninguna película.");
        } else {
            System.out.println("--- Listado de películas ---");
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
                System.out.println("--------------------");
            }
        }
    }
}