package dev.ivan.views.moment;

import java.util.List;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.moment.EmotionEnum;
import dev.ivan.singletons.MomentControllerSingleton;
import dev.ivan.views.HomeView;
import dev.ivan.views.View;

public class MomenFilterByEmotionView extends View {

    private static final MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printFilterMenu() {
        System.out.println("Seleccione la emoción para filtrar:");

        for (EmotionEnum e : EmotionEnum.values()) {
            System.out.println((e.ordinal() + 1) + ". " + e.getDisplayName());
        }

        int choice = SCANNER.nextInt();

        if (choice < 1 || choice > EmotionEnum.values().length) {
            System.out.println("Opción inválida.");
            HomeView.printMenu();
            return;
        }

        EmotionEnum selectedEmotion = EmotionEnum.values()[choice - 1];


        List<MomentResponseDTO> moments = CONTROLLER.showMomentsByEmotion(selectedEmotion);

        AllMomentsView.printMoments(moments);

        HomeView.printMenu();
    }
}
