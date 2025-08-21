package dev.ivan.views;

import java.util.Scanner;

public class MovieDeleteView {

    public int display() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca el número de la película a eliminar: ");
        int index = -1;
        try {
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, introduzca un número.");
        }
        return index;
    }

    public void printSuccess() {
        System.out.println("Pelicula eliminada con éxito.");
    }

    public void printInvalidIndex() {
        System.out.println("Índice inválido.");
    }

    public void printNoMoviesToDelete() {
        System.out.println("No hay películas para eliminar.");
    }
}