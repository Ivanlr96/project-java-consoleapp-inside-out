package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.singletons.MomentControllerSingleton;

public class HomeView extends View {
    
    public static final MomentController momentController = MomentControllerSingleton.getInstance();

    public static void printMenu() {

        String text = """
                1. Añadir momento
                2. Ver todos los momentos disponibles
                3. Eliminar un momento
                4. Filtrar los momentos
                5. Salir
                Seleccione una opción:
                """;

        System.out.println(text);

        int option = SCANNER.nextInt();

        if (option == 1) {
            MomentPostView.printStoreMenu();
        }
        if (option == 2) {
            momentController.ShowAllMoments();
            printMenu();
        }
        if (option == 3) {
            MomentDeleteView.printDeleteMenu();
        }

        if (option == 4) {
        MomentFilterView.printFilterMenu();
        }
        
        if (option == 5) {
            System.out.println("Saliendo...");
            System.exit(0);
        }

       if (option < 1 || option > 5) {
            System.out.println("Opción inválida. Inténtalo de nuevo.");
            printMenu();
        }
    }

}
