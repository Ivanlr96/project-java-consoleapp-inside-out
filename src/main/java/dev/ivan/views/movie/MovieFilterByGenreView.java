package dev.ivan.views.movie;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieFilterByGenreView {

    private static final List<String> IMDB_GENRES = Arrays.asList(
            "Action", "Adult", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary",
            "Drama", "Family", "Fantasy", "Film-Noir", "Game Show", "History", "Horror",
            "Musical", "Music", "Mystery", "News", "Reality-TV", "Romance", "Sci-Fi", "Short",
            "Sport", "Talk-Show", "Thriller", "War", "Western"
    );

    public String display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Filtrar películas por género ---");
        System.out.println("Seleccione un género:");

        for (int i = 0; i < IMDB_GENRES.size(); i++) {
            System.out.println((i + 1) + ". " + IMDB_GENRES.get(i));
        }

        int choice = -1;
        while (choice < 1 || choice > IMDB_GENRES.size()) {
            System.out.print("Introduzca su elección: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduzca un número.");
            }
        }

        return IMDB_GENRES.get(choice - 1);
    }
}
