package dev.ivan.views.movie;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.movie.Movie;
import dev.ivan.views.HomeView;

import java.util.Scanner;

public class MoviePostView {

    public String[] display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Añadir Película ---");
        System.out.print("Introduzca la IMDBID: ");
        String imdbId = scanner.nextLine();

        System.out.println("Seleccione una emoción:");
        for (EmotionEnum emotion : EmotionEnum.values()) {
            System.out.println((emotion.ordinal() + 1) + ". " + emotion.getDisplayName());
        }

        int choice = -1;
        while (choice < 1 || choice > EmotionEnum.values().length) {
            System.out.print("Introduzca el número de la emoción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Por favor, introduzca un número.");
            }
        }

        EmotionEnum selectedEmotion = EmotionEnum.values()[choice - 1];

        return new String[]{imdbId, selectedEmotion.name()};
    }

    public void printSuccess(Movie movie) {
        System.out.println("Pelicula añadida con éxito:");
        System.out.println(movie);
        HomeView.printMenu();
    }

    public void printError() {
        System.out.println("Error al añadir la película.");
        HomeView.printMenu();
    }
}
