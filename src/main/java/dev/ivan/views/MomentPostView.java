package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.views.HomeView;
import dev.ivan.models.EmotionEnum;

public class MomentPostView extends View {

    private static MomentController CONTROLLER = HomeView.momentController;

    public static void printStoreMenu() {
        System.out.println("Ingrese el título:");
        String title = SCANNER.next();
        System.out.println("Ingrese la fecha: (dd/mm/aaaa)");
        String date = SCANNER.next();
        System.out.println("Ingrese la descripción:");
        String description = SCANNER.next();

        System.out.println("Seleccione la emoción:");
        for (EmotionEnum e : EmotionEnum.values()) {
            System.out.println((e.ordinal() + 1) + ". " + e.getDisplayName());
        }
        int emotionChoice = SCANNER.nextInt();
        EmotionEnum emotion = EmotionEnum.values()[emotionChoice - 1];

        MomentDTO momentDTO = new MomentDTO(title, date, description, emotion);
        CONTROLLER.StoreMoment(momentDTO);

        HomeView.printMenu();
    }
}
