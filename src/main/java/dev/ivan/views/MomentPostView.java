package dev.ivan.views;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.views.HomeView;
import dev.ivan.models.EmotionEnum;
import dev.ivan.singletons.MomentControllerSingleton;

public class MomentPostView extends View {

  private static MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printStoreMenu() {
        System.out.println("Ingrese el título:");
        String title = SCANNER.next();
             LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (date == null) {
            System.out.println("Ingrese la fecha (dd/MM/yyyy):");
            String dateInput = SCANNER.next();
            try {
                date = LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido, intenta de nuevo.");
            }
        }

        System.out.println("Ingrese la descripción:");
        String description = SCANNER.next();

        System.out.println("Seleccione la emoción:");
        for (EmotionEnum e : EmotionEnum.values()) {
            System.out.println((e.ordinal() + 1) + ". " + e.getDisplayName());
        }
        int emotionChoice = SCANNER.nextInt();
        EmotionEnum emotion = EmotionEnum.values()[emotionChoice - 1];

        MomentDTO momentDTO = new MomentDTO(title, date, description, emotion);
        CONTROLLER.storeMoment(momentDTO);
        System.out.println("Momento vivido añadido correctamente.");

        HomeView.printMenu();
    }
}
