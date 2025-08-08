package dev.ivan.views;

public class HomeView extends View{

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
    }

}
