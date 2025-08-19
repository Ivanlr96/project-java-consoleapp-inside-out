package dev.ivan.views;

import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.models.EmotionEnum;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AllMomentsViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintNoMomentsMessageWhenListIsEmpty() {
        AllMomentsView.printMoments(List.of());

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No hay momentos guardados."));
    }

    @Test
    void shouldPrintMomentsWhenListIsNotEmpty() {
        MomentResponseDTO moment = new MomentResponseDTO(   
                "Mi título",
                "12/05/2020",
                "Una descripción",
                EmotionEnum.ALEGRIA
        );

        AllMomentsView.printMoments(List.of(moment));

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Momentos guardados:"));
        assertTrue(output.contains("Ocurrió el: 12/05/2020"));
        assertTrue(output.contains("Título: Mi título"));
        assertTrue(output.contains("Descripción: Una descripción"));
    }
}
