package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.singletons.MomentControllerSingleton;

import java.util.List;

public class MomentDeleteView extends View {

    private static MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printDeleteMenu() {

        List<MomentResponseDTO> moments = CONTROLLER.getAllMoments();

        if (moments.isEmpty()) {
            System.out.println("No hay momentos guardados.");
            HomeView.printMenu();
            return;
        }

        System.out.println("Momentos guardados:");
        for (int i = 0; i < moments.size(); i++) {
            MomentResponseDTO m = moments.get(i);
            System.out.println((i + 1) + ". " +
                    "Ocurrió el: " + m.date() + ". " +
                    "Título: " + m.title() + ". " +
                    "Descripción: " + m.description() + ". ");

        }

        System.out.println("Seleccione el número del momento que desea eliminar:");
        int choice = SCANNER.nextInt();

        boolean deleted = CONTROLLER.deleteMoment(choice - 1);

        if (deleted) {
            System.out.println("Momento eliminado correctamente.");
        } else {
            System.out.println("Número de momento inválido");
        }

        HomeView.printMenu();
    }
}
