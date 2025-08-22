package dev.ivan.views.moment;

import dev.ivan.controllers.MomentController;
import dev.ivan.singletons.MomentControllerSingleton;
import dev.ivan.views.HomeView;
import dev.ivan.views.View;

public class MomentCSVStoreView extends View {

    public static final MomentController CONTROLLER = MomentControllerSingleton.getInstance();

    public static void printExportMenu() {
        System.out.println("¿Desea exportar todos los momentos a CSV?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int choice = SCANNER.nextInt();
        if (choice == 1) {
            boolean success = CONTROLLER.exportMomentsToCSV();
            if (success) {
                System.out.println("Momento(s) exportado(s) correctamente.");
                HomeView.printMenu();
            } else {
                System.out.println("Error al exportar los momentos.");
                HomeView.printMenu();
            }
        } else if (choice == 2) {
            System.out.println("Operación cancelada.");
            HomeView.printMenu();
        } else {
            System.out.println("Opción inválida.");
            HomeView.printMenu();
        }

    }
}
