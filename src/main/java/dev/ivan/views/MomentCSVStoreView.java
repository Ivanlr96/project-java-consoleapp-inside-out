package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.singletons.MomentControllerSingleton;

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
            } else {
                System.out.println("Error al exportar los momentos.");
            }
        } else if (choice == 2) {
            System.out.println("Operación cancelada.");
        } else {
            System.out.println("Opción inválida.");
        }



     }
    }
