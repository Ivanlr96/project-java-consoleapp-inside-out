package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.singletons.MomentControllerSingleton;

public class HomeView extends View {

    public static final MomentController momentController = MomentControllerSingleton.getInstance();
    public static void printExportMenu() {
    System.out.println("¿Desea exportar todos los momentos a CSV?");
    System.out.println("1. Sí");
    System.out.println("2. No");

    int choice = SCANNER.nextInt();
    switch (choice) {
        case 1:
            boolean success = momentController.exportMomentsToCSV();
            if (success) {
                System.out.println("Momento(s) exportado(s) correctamente.");
            } else {
                System.out.println("Error al exportar los momentos.");
            }
            break;
        case 2:
            System.out.println("Operación cancelada.");
            break;
        default:
            System.out.println("Opción inválida.");
    }
}

    public static void printMenu() {

        String text = """
                Mi diario:
                1. Añadir momento
                2. Ver todos los momentos disponibles
                3. Eliminar un momento
                4. Filtrar los momentos
                5. Exportar a CSV
                6. Salir
                Seleccione una opción:
                """;

        System.out.println(text);

        int option = SCANNER.nextInt();

        if (option == 1) {
            MomentPostView.printStoreMenu();
        }
        if (option == 2) {
        momentController.showAllMoments();
        }
        if (option == 3) {
        MomentDeleteView.printDeleteMenu();
        }

        if (option == 4) {
        MomentFilterView.printFilterMenu();
        }
        if (option == 5) {
        printExportMenu();
        }

        if (option == 6) {
            System.out.println("¡Hasta la proxima!");
            System.exit(0);
        }

       if (option < 1 || option > 6) {
            System.out.println("Opción inválida. Inténtalo de nuevo.");
            printMenu();
        }
    }

}
