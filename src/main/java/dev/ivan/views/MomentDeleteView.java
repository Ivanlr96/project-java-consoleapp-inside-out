package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.models.Moment;

import java.util.List;

public class MomentDeleteView extends View {

    private static MomentController CONTROLLER = HomeView.momentController;

    public static void printDeleteMenu() {

        List<Moment> moments = CONTROLLER.getAllMoments();
        CONTROLLER.ShowAllMoments();

        if (moments.isEmpty()) {
            System.out.println("No hay momentos guardados.");
            HomeView.printMenu();
            return;
        }

        System.out.println("Momentos guardados:");
        for (int i = 0; i < moments.size(); i++) {
            System.out.println((i + 1) + ". " + moments.get(i));
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
