package dev.ivan.views;

import dev.ivan.models.moment.EmotionEnum;

import java.util.Scanner;

public class MoviePostView {

    public String[] display() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Añadir una película: ");
        System.out.print("Introduzca la IMDB ID: ");
        String imdbId = scanner.nextLine();

        System.out.println("Seleccione una emoción:");
        for (EmotionEnum emotion : EmotionEnum.values()) {
            System.out.println((emotion.ordinal() + 1) + ". " + emotion.getDisplayName());
        }

        int choice = -1;
        while (choice < 1 || choice > EmotionEnum.values().length) {
            System.out.print("Número de la emoción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, introduzca un número válido.");
            }
        }

        EmotionEnum selectedEmotion = EmotionEnum.values()[choice - 1];

        return new String[]{imdbId, selectedEmotion.name()};
    }
}
