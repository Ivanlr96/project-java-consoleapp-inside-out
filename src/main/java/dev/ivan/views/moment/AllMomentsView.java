package dev.ivan.views.moment;

import java.util.List;

import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.views.HomeView;
import dev.ivan.views.View;

public class AllMomentsView extends View {

    public static void printMoments(List<MomentResponseDTO> moments) {
        if (moments == null || moments.isEmpty()) {
            System.out.println("No hay momentos guardados.");
            return;
        }

        System.out.println("Momentos guardados:");
        int index = 1;
        for (MomentResponseDTO m : moments) {
            System.out.println(
                index++ + ". " +
                "Ocurrió el: " + m.date() + ". " +
                "Título: " + m.title() + ". " +
                "Descripción: " + m.description() + ". " +
                "Tipo: " + m.type()
            );
        }

        HomeView.printMenu();
    }
}
