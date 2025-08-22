package dev.ivan.views.moment;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.models.moment.MomentTypeEnum;
import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.singletons.MomentControllerSingleton;
import dev.ivan.views.HomeView;
import dev.ivan.views.View;

public class MomentPostView extends View {

    private static MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printStoreMenu() {
        SCANNER.nextLine();

        System.out.println("Ingrese el título:");
        String title = SCANNER.nextLine();

        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (date == null) {
            System.out.println("Ingrese la fecha (dd/MM/yyyy):");
            String dateInput = SCANNER.nextLine();
            try {
                date = LocalDate.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido, intenta de nuevo.");
            }
        }

        System.out.println("Ingrese la descripción:");
        String description = SCANNER.nextLine();

        System.out.println("Seleccione la emoción:");
        for (EmotionEnum e : EmotionEnum.values()) {
            System.out.println((e.ordinal() + 1) + ". " + e.getDisplayName());
        }
        int emotionChoice = SCANNER.nextInt();
        EmotionEnum emotion = EmotionEnum.values()[emotionChoice - 1];

        System.out.println("Seleccione si el momento fue bueno o malo:");
        System.out.println("1. Bueno");
        System.out.println("2. Malo");

        int typeChoice = SCANNER.nextInt();
        MomentTypeEnum type = (typeChoice == 1) ? MomentTypeEnum.Bueno : MomentTypeEnum.Malo;

        MomentDTO momentDTO = new MomentDTO(title, date, description, emotion, type);
        CONTROLLER.storeMoment(momentDTO);
        System.out.println("Momento vivido añadido correctamente.");

        HomeView.printMenu();
    }

}
