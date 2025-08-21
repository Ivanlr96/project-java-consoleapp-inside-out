package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.moment.MomentTypeEnum;
import dev.ivan.singletons.MomentControllerSingleton;

import java.util.List;

public class MomentFilterByTypeView extends View {

    private static final MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printFilterMenu() {
        System.out.println("Seleccione el tipo de momento:");
        for (MomentTypeEnum type : MomentTypeEnum.values()) {
            System.out.println((type.ordinal() + 1) + ". " + type.name());
        }

        int choice = SCANNER.nextInt();

        if (choice < 1 || choice > MomentTypeEnum.values().length) {
            System.out.println("Opción inválida.");
            HomeView.printMenu();
            return;
        }

        MomentTypeEnum selectedType = MomentTypeEnum.values()[choice - 1];
        List<MomentResponseDTO> moments = CONTROLLER.showMomentsByType(selectedType);

        AllMomentsView.printMoments(moments);

        HomeView.printMenu();
    }
}
