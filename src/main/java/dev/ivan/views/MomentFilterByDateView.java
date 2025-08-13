package dev.ivan.views;

import dev.ivan.controllers.MomentController;
import dev.ivan.models.Moment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MomentFilterByDateView extends View {

    private static MomentController CONTROLLER = HomeView.momentController;

    public static void printFilterMenu() {
        System.out.println("Ingrese la fecha para filtrar (dd/MM/yyyy):");
        String inputDate = SCANNER.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(inputDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido.");
            HomeView.printMenu();
            return;
        }

        List<Moment> moments = CONTROLLER.getMomentsByDate(date);

        if (moments.isEmpty()) {
            System.out.println("No se encontraron momentos con la fecha ingresada.");
        } else {
            System.out.println("Momentos encontrados:");
            moments.forEach(System.out::println);
        }

        HomeView.printMenu();
    }
}
