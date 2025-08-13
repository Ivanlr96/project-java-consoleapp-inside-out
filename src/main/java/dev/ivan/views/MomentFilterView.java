package dev.ivan.views;

public class MomentFilterView extends View {

    public static void printFilterMenu() {
        System.out.println("Filtrar por ...:");
        System.out.println("1. Emoción");
        System.out.println("2. Fecha");

        int choice = SCANNER.nextInt();

        if (choice == 1) {
            MomenFilterByEmotionView.printFilterMenu();
        } else if (choice == 2) {
            MomentFilterByDateView.printFilterMenu();
        } else if (choice == 3) {
            HomeView.printMenu();
        } else {
            System.out.println("Opción inválida. Intentelo de nuevo.");
            printFilterMenu();
        }
    }
}
