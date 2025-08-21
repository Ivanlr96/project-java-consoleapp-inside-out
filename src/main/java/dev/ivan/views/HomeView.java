package dev.ivan.views;

import dev.ivan.controllers.MovieController;
import dev.ivan.controllers.MomentController;
import dev.ivan.singletons.MomentControllerSingleton;
import dev.ivan.singletons.MovieControllerSingleton;
import dev.ivan.views.moment.MomentCSVStoreView;
import dev.ivan.views.moment.MomentDeleteView;
import dev.ivan.views.moment.MomentFilterView;
import dev.ivan.views.moment.MomentPostView;

public class HomeView extends View {

    public static final MomentController momentController = MomentControllerSingleton.getInstance();
    public static final MovieController movieController = MovieControllerSingleton.getInstance();

    public static void printMenu() {

        String text = """
                Mi diario:
                1. Añadir momento
                2. Ver todos los momentos disponibles
                3. Eliminar un momento
                4. Filtrar los momentos
                5. Exportar a CSV
                6. Añadir película
                7. Ver todas las películas
                8. Eliminar película
                9. Filtrar películas por género
                10. Salir
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
            MomentCSVStoreView.printExportMenu();
        }
        if (option == 6) {
            movieController.addMovie();
        }
        if (option == 7) {
            movieController.showAllMovies();
        }
        if (option == 8) {
            movieController.deleteMovie();
        }
        if (option == 9) {
            movieController.showMoviesByGenre();
        }
        if (option == 10) {
            System.out.println("¡Hasta la proxima!");
            System.exit(0);
        }

        if (option < 1 || option > 10) {
            System.out.println("Opción inválida. Inténtalo de nuevo.");
            printMenu();
        }
    }
}
