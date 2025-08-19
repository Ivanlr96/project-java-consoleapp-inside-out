package dev.ivan.views;

import java.util.List;

import dev.ivan.dtos.MomentResponseDTO;

public class AllMomentsView extends View {

    public static void printMoments(List<MomentResponseDTO> moments) {
        if (moments == null || moments.isEmpty()) {
            System.out.println("No hay momentos guardados.");
            return;
        }

        System.out.println("Momentos guardados:");
        for (MomentResponseDTO m : moments) {
            System.out.println("Título: " + m.title());
            System.out.println("Fecha: " + m.date());
            System.out.println("Descripción: " + m.description());
            System.out.println("Emoción: " + m.emotion().getDisplayName());

        }
        HomeView.printMenu();
    }

}
