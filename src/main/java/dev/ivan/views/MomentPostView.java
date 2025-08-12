package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.views.HomeView;
public class MomentPostView extends View {

    private static MomentController CONTROLLER = HomeView.momentController;

    public static void printStoreMenu() {
        System.out.println("Ingrese el título:");
        String title = SCANNER.next();
        System.out.println("Ingrese la fecha: (dd/mm/aaaa)");
        String date = SCANNER.next();
        System.out.println("Ingrese la descripción:");
        String description = SCANNER.next();

        MomentDTO momentDTO = new MomentDTO(title, date, description);
        CONTROLLER.StoreMoment(momentDTO);
        HomeView.printMenu();

    }
}
